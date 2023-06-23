package io.luxus.adofai.helper.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class ViewModel<S>(initialState: S) {

    var state: S by mutableStateOf(initialState)
        protected set

    protected inline fun setState(update: S.() -> S) {
        state = state.update()
    }

}
