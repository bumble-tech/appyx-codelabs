package com.bumble.appyx_codelabs.simpleapp.solution.state

import com.bumble.appyx_codelabs.simpleapp.solution.state.LoggingStateSource.LogginState.LOGGED_IN
import com.bumble.appyx_codelabs.simpleapp.solution.state.LoggingStateSource.LogginState.LOGGED_OUT
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoggingStateSourceImpl : LoggingStateSource {

    private val _loggingState = MutableStateFlow(LOGGED_OUT)

    override val loggingState: StateFlow<LoggingStateSource.LogginState>
        get() = _loggingState

    override fun logIn() {
        _loggingState.value = LOGGED_IN
    }

    override fun logOut() {
        _loggingState.value = LOGGED_OUT
    }
}
