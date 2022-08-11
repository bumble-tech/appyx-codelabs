package com.bumble.appyx_codelabs.simpleapp.root

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
import com.bumble.appyx.routingsource.backstack.activeRouting
import com.bumble.appyx.routingsource.backstack.operation.pop
import com.bumble.appyx.routingsource.backstack.operation.push
import com.bumble.appyx.routingsource.backstack.transitionhandler.rememberBackstackFader
import com.bumble.appyx_codelabs.simpleapp.loggedin.LoggedInNode
import com.bumble.appyx_codelabs.simpleapp.loggedout.LoggedOutNode
import com.bumble.appyx_codelabs.simpleapp.root.RootNode.Routing
import com.bumble.appyx_codelabs.simpleapp.root.RootNode.Routing.LoggedOut
import com.bumble.appyx_codelabs.simpleapp.root.RootNode.Routing.LoggedIn
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<Routing> = BackStack(
        initialElement = LoggedOut,
        savedStateMap = buildContext.savedStateMap
    )
) : ParentNode<Routing>(
    buildContext = buildContext,
    routingSource = backStack
) {

    sealed class Routing : Parcelable {
        @Parcelize
        object LoggedOut : Routing()

        @Parcelize
        object LoggedIn : Routing()
    }

    override fun resolve(routing: Routing, buildContext: BuildContext): Node =
        when (routing) {
            is LoggedOut -> LoggedOutNode(buildContext, ::swapChildren)
            is LoggedIn -> LoggedInNode(buildContext)
        }

    private fun swapChildren() {
        if (backStack.activeRouting == LoggedOut) {
            backStack.push(LoggedIn)
        } else {
            backStack.pop()
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
