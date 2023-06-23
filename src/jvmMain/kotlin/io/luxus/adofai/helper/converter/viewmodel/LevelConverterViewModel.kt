package io.luxus.adofai.helper.converter.viewmodel

import io.luxus.adofai.helper.app.viewmodel.ViewModel
import io.luxus.adofai.lib.CustomLevel
import java.io.File

class LevelConverterViewModel : ViewModel<LevelConverterViewModel.State>(State()) {

    fun showFilePicker() {
        setState {
            copy(showFilePicker = true)
        }
    }

    fun onSelectFile(file: File?) {
        val (customLevel, exceptions) = file?.let { CustomLevel.readDetailed(file.inputStream()) }
            ?: Pair(null, listOf())

        setState {
            copy(
                showFilePicker = false,
                file = file,
                customLevel = customLevel,
                customLevelReadExceptions = exceptions,
            )
        }
    }

    fun onConvert(customLevel: CustomLevel) {
        setState {
            copy(
                customLevel = customLevel,
                converted = true
            )
        }
    }

    data class State(
        val showFilePicker: Boolean = false,
        val file: File? = null,
        val customLevel: CustomLevel? = null,
        val converted: Boolean = false,
        val customLevelReadExceptions: List<Exception> = listOf(),
    )
}
