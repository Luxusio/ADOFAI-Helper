package io.luxus.adofai.helper.converter.viewmodel

import io.luxus.adofai.helper.app.viewmodel.ViewModel
import io.luxus.adofai.helper.converter.model.TimingBasedCustomLevelConverter
import io.luxus.adofai.lib.CustomLevel

class OuterLevelConverterViewModel(
    customLevel: CustomLevel
) : ViewModel<OuterLevelConverterViewModel.State>(
    State(
        customLevel = customLevel
    )
) {

    fun convert(): CustomLevel = TimingBasedCustomLevelConverter.convertOuter(state.customLevel)


    data class State(
        val customLevel: CustomLevel,
    )
}
