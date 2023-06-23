package io.luxus.adofai.helper.converter.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import dev.icerock.moko.resources.compose.stringResource
import io.luxus.adofai.helper.MR
import io.luxus.adofai.helper.converter.viewmodel.LevelConverterViewModel
import java.io.File


@Preview
@Composable
fun LevelConverter(modifier: Modifier = Modifier) {
    val viewModel = remember { LevelConverterViewModel() }
    val state = viewModel.state

    FilePicker(state.showFilePicker, fileExtensions = listOf("adofai"), initialDirectory = state.file?.parent) { path ->
        viewModel.onSelectFile(path?.platformFile as File?)
    }

    Column(Modifier.fillMaxSize().background(Color.LightGray)) {
        Row(
            Modifier.fillMaxWidth().background(Color.Gray),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = state.customLevel?.let {
                "${it.levelSetting.artist} - ${it.levelSetting.song}"
            } ?: "",
                modifier = Modifier.padding(6.dp).padding(start = 24.dp)
                    .weight(1f))

            Button(onClick = {
                viewModel.showFilePicker()
            }, modifier = Modifier.padding(6.dp).padding(end = 12.dp)) {
                Text(stringResource(MR.strings.open_file))
            }
        }

        state.customLevel?.let {
            OuterLevelConverter(it, onConvert = {
                viewModel.onConvert(it)
            })
        }

        if (state.converted) {
            Button(onClick = {
                viewModel.showFilePicker()
            }, modifier = Modifier.padding(6.dp).padding(end = 12.dp)) {
                Text(stringResource(MR.strings.save))
            }
        }
    }

}
