package com.bumble.appyx_codelabs.simpleapp.solution.state

import kotlinx.coroutines.flow.StateFlow

interface LoggingStateSource {

    val loggingState: StateFlow<LogginState>

    fun logIn()

    fun logOut()


    enum class LogginState{
        LOGGED_OUT,
        LOGGED_IN
    }
}
