package io.luxus.adofai.lib

import com.fasterxml.jackson.databind.ObjectMapper
import io.luxus.adofai.lib.action.Decoration
import io.luxus.adofai.lib.json.AdofaiJsonInputStream
import io.luxus.adofai.lib.parser.CustomLevelReader
import io.luxus.adofai.lib.parser.CustomLevelWriter
import java.io.InputStream
import java.io.OutputStream

data class CustomLevel(
    val levelSetting: CustomLevelSetting,
    val tiles: List<Tile>,
    val decorations: List<Decoration>,
) {

    fun toBuilder() = Builder()
        .levelSetting(levelSetting)
        .tiles(tiles)
        .decorations(decorations)

    fun write(outputStream: OutputStream) {
        CustomLevelWriter.write(this, outputStream)
    }

    companion object {
        val objectMapper = ObjectMapper()

        fun read(inputStream: InputStream): CustomLevel = readDetailed(inputStream).first

        fun readDetailed(inputStream: InputStream): Pair<CustomLevel, List<Exception>> {
            val adofaiJsonInputStream = AdofaiJsonInputStream(inputStream)
            val jsonNode = objectMapper.readTree(adofaiJsonInputStream)
            return CustomLevelReader.INSTANCE.read(jsonNode)
        }
    }

    class Builder {
        var levelSettingBuilder: CustomLevelSetting.Builder = CustomLevelSetting.Builder()
            private set

        private var _tileBuilders: MutableList<Tile.Builder> = MutableList(11) { Tile.Builder().angle(TileAngle._0) }
        val tileBuilders: List<Tile.Builder>
            get() = _tileBuilders.toList()


        private var _decorations: MutableList<Decoration> = mutableListOf()
        val decorations: List<Decoration>
            get() = _decorations.toList()

        fun levelSetting(levelSetting: CustomLevelSetting) =
            apply { this.levelSettingBuilder = levelSetting.toBuilder() }

        fun levelSettingBuilder(levelSettingBuilder: CustomLevelSetting.Builder) =
            apply { this.levelSettingBuilder = levelSettingBuilder }

        fun tiles(tiles: List<Tile>) = apply { this._tileBuilders = tiles.map { it.toBuilder() }.toMutableList() }

        fun tileBuilders(tileBuilders: List<Tile.Builder>) = apply { this._tileBuilders = tileBuilders.toMutableList() }

        fun decorations(decorations: List<Decoration>) = apply { this._decorations = decorations.toMutableList() }

        fun build(): CustomLevel {
            val customLevelSetting = levelSettingBuilder.build()
            return CustomLevel(
                levelSetting = customLevelSetting,
                tiles = Tile.Builder.build(tileBuilders, customLevelSetting),
                decorations = decorations,
            )
        }
    }
}
