package io.luxus.adofai.lib

class Tile private constructor(
    val angle: TileAngle,
    val actionMap: ActionMap,
    val tileMeta: TileMeta,
) {

    fun toBuilder() = Builder()
        .angle(angle)
        .actionMap(actionMap)

    class Builder {
        var angle = TileAngle._0
            private set
        var actionMap: MutableActionMap = mutableActionMapOf()
            private set

        fun angle(angle: TileAngle) = apply { this.angle = angle }
        fun actionMap(actionMap: ActionMap): Builder = apply { this.actionMap = actionMap.toMutableActionMap() }

        fun buildFirst(customLevelSetting: CustomLevelSetting, nextAngle: TileAngle): Tile {
            val tileMeta = TileMeta.createFirst(actionMap, customLevelSetting, nextAngle)
            return Tile(nextAngle, actionMap.toActionMap(), tileMeta)
        }

        fun buildAfterFirst(prevTileMeta: TileMeta, nextAngle: TileAngle?): Tile {
            val tileMeta: TileMeta = TileMeta.createAfterFirst(
                actionMap, prevTileMeta, angle,
                nextAngle ?: if (angle.midSpin) TileAngle.create(prevTileMeta.absoluteAngle) else angle
            )
            return Tile(angle, actionMap.toActionMap(), tileMeta)
        }

        companion object {
            fun build(tileBuilders: List<Builder>, customLevelSetting: CustomLevelSetting): List<Tile> {
                when (tileBuilders.size) {
                    0 -> return listOf()
                    1 -> return listOf(tileBuilders[0].buildFirst(customLevelSetting, TileAngle._0))
                }

                val results: MutableList<Tile> = mutableListOf()
                val bIt = tileBuilders.iterator()

                // build first tile
                val firstTile = bIt.next()
                    .buildFirst(customLevelSetting, tileBuilders[1].angle)

                results.add(firstTile)

                // build rest tiles
                if (!bIt.hasNext()) return results

                var prevTileMeta: TileMeta = firstTile.tileMeta
                var currTileBuilder = bIt.next()
                while (bIt.hasNext()) {
                    val nextTileBuilder = bIt.next()
                    val newCurrTile: Tile = currTileBuilder.buildAfterFirst(prevTileMeta, nextTileBuilder.angle)
                    results.add(newCurrTile)
                    prevTileMeta = newCurrTile.tileMeta
                    currTileBuilder = nextTileBuilder
                }

                val lastTile: Tile = currTileBuilder.buildAfterFirst(prevTileMeta, null)
                results.add(lastTile)

                return results.toList()
            }
        }
    }

}
