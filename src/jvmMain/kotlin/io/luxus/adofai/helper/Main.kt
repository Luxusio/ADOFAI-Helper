package io.luxus.adofai.helper

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.luxus.adofai.helper.converter.view.LevelConverter

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            LevelConverter()
        }
    }
}
