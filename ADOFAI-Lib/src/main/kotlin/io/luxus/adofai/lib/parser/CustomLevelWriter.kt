package io.luxus.adofai.lib.parser

import io.luxus.adofai.lib.CustomLevel
import io.luxus.adofai.lib.action.Action
import io.luxus.adofai.lib.action.Decoration
import io.luxus.adofai.lib.action.EventType
import io.luxus.adofai.lib.action.UnknownAction
import io.luxus.adofai.lib.json.ObjectToJsonStringApplier
import io.luxus.adofai.lib.json.toCompactString
import java.io.BufferedWriter
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets


class CustomLevelWriter {
    companion object {
        fun write(customLevel: CustomLevel, outputStream: OutputStream) {
            BufferedWriter(OutputStreamWriter(outputStream, StandardCharsets.UTF_8)).use { bw ->
                bw.write(65279) // write BOM

                bw.write("{\n\t\"angleData\": [")
                customLevel.tiles
                    .filterIndexed { index, _ -> index != 0 }
                    .map { it.angle }
                    .map { if (it.midSpin) 999.0 else it.angle }
                    .forEachIndexed { index, angle ->
                        if (index == 0) {
                            bw.write(angle.toCompactString())
                        } else {
                            bw.write(", ${angle.toCompactString()}")
                        }
                    }

                bw.write("],\n\t\"settings\":\n\t{")
                var first = true
                ObjectToJsonStringApplier.apply(
                    customLevel.levelSetting,
                    { it != "unknownProperties" }
                ) {
                    if (first) {
                        first = false
                        bw.write("\n\t\t${it}")
                    } else {
                        bw.write(",\n\t\t${it}")
                    }
                }

                customLevel.levelSetting.unknownProperties.entries.forEach { (key, value) ->
                    val sb = StringBuilder()

                    ObjectToJsonStringApplier.writeVar(sb, key)
                    sb.append(": ")
                    ObjectToJsonStringApplier.writeVar(sb, value)

                    if (first) {
                        first = false
                        bw.write("\n\t\t${sb}")
                    } else {
                        bw.write(",\n\t\t${sb}")
                    }
                }

                bw.write("\n\t},\n\t\"actions\":\n\t[")
                first = true
                customLevel.tiles.forEach { tile ->
                    tile.actionMap.values.forEach { action ->
                        if (first) {
                            first = false
                        } else {
                            bw.write(",")
                        }

                        writeAction(bw, action, tile.tileMeta.floor)
                    }
                }

                first = true
                bw.write("\n\t],\n\t\"decorations\":\n\t[")
                customLevel.decorations.forEach { decoration ->
                    if (first) {
                        first = false
                    } else {
                        bw.write(",")
                    }

                    writeAction(bw, decoration, decoration.floor?.toInt())
                }

                bw.write("\n\t]\n}\n")
            }
        }

        fun writeAction(bw: BufferedWriter, action: Action, floor: Int?) {
            bw.write("\n\t\t{ ")
            val eventType = action.eventType.getAnnotation(EventType::class.java).jsonValue

            when (action) {
                is UnknownAction -> {
                    var firstField = true
                    ObjectToJsonStringApplier.apply(action.rawData) {
                        if (firstField) {
                            firstField = false
                            bw.write(it)
                        } else {
                            bw.write(", $it")
                        }
                    }
                }

                is Decoration -> {
                    floor?.let { bw.write("\"floor\": $it, ") }
                    bw.write("\"eventType\": \"$eventType\"")
                    ObjectToJsonStringApplier.apply(action, { it != "floor" }) { bw.write(", $it") }
                }

                else -> {
                    bw.write("\"floor\": $floor, \"eventType\": \"$eventType\"")
                    ObjectToJsonStringApplier.apply(action) { bw.write(", $it") }
                }
            }

            bw.write(" }")
        }
    }
}
