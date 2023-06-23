package io.luxus.adofai.helper.converter.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.stringResource
import io.luxus.adofai.helper.MR
import io.luxus.adofai.helper.converter.viewmodel.OuterLevelConverterViewModel
import io.luxus.adofai.lib.CustomLevel

@Preview
@Composable
fun OuterLevelConverter(
    customLevel: CustomLevel,
    onConvert: (CustomLevel) -> Unit,
) {
    val viewModel = remember { OuterLevelConverterViewModel(customLevel) }

    Column {
        Text(stringResource(MR.strings.outer_converter), fontWeight = FontWeight.Black, fontSize = 24.sp)

        Button(onClick = { onConvert(viewModel.convert()) }) {
            Text(stringResource(MR.strings.convert))
        }
    }
}
