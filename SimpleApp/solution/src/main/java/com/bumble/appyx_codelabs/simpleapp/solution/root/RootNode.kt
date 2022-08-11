package com.bumble.appyx_codelabs.simpleapp.solution.root

import android.os.Parcelable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.coroutineScope
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.routingsource.backstack.BackStack
import com.bumble.appyx.routingsource.backstack.operation.replace
import com.bumble.appyx.routingsource.backstack.transitionhandler.rememberBackstackFader
import com.bumble.appyx_codelabs.simpleapp.solution.loggedin.LoggedInNode
import com.bumble.appyx_codelabs.simpleapp.solution.loggedout.LoggedOutNode
import com.bumble.appyx_codelabs.simpleapp.solution.root.RootNode.Routing
import com.bumble.appyx_codelabs.simpleapp.solution.root.RootNode.Routing.LoggedIn
import com.bumble.appyx_codelabs.simpleapp.solution.root.RootNode.Routing.LoggedOut
import com.bumble.appyx_codelabs.simpleapp.solution.state.LoggingStateSource
import com.bumble.appyx_codelabs.simpleapp.solution.state.LoggingStateSource.LogginState.LOGGED_IN
import com.bumble.appyx_codelabs.simpleapp.solution.state.LoggingStateSource.LogginState.LOGGED_OUT
import com.bumble.appyx_codelabs.simpleapp.solution.state.LoggingStateSourceImpl
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<Routing> = BackStack(
        initialElement = LoggedOut,
        savedStateMap = buildContext.savedStateMap
    ),
    private val loggingStateSource: LoggingStateSource = LoggingStateSourceImpl()
) : ParentNode<Routing>(
    buildContext = buildContext,
    routingSource = backStack
) {

    init {
        collectLogginState()
    }

    sealed class Routing : Parcelable {
        @Parcelize
        object LoggedOut : Routing()

        @Parcelize
        object LoggedIn : Routing()
    }

    override fun resolve(routing: Routing, buildContext: BuildContext): Node =
        when (routing) {
            is LoggedOut -> LoggedOutNode(buildContext) { loggingStateSource.logIn() }
            is LoggedIn -> LoggedInNode(buildContext) { loggingStateSource.logOut() }
        }

    private fun collectLogginState() {
        lifecycle.coroutineScope.launch {
            loggingStateSource.loggingState.collect { state ->
                when (state) {
                    LOGGED_OUT -> backStack.replace(LoggedOut)
                    LOGGED_IN -> backStack.replace(LoggedIn)
                }
            }
        }
    }

    @Composable
    override fun View(modifier: Modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Children(
                routingSource = backStack,
                transitionHandler = rememberBackstackFader { spring(stiffness = Spring.StiffnessVeryLow) },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
