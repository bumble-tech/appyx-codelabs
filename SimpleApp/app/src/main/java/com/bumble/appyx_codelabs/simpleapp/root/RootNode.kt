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
import com.bumble.appyx_codelabs.simpleapp.loggedin.ChildNode1
import com.bumble.appyx_codelabs.simpleapp.loggedout.ChildNode2
import com.bumble.appyx_codelabs.simpleapp.root.RootNode.Routing
import com.bumble.appyx_codelabs.simpleapp.root.RootNode.Routing.Child1
import com.bumble.appyx_codelabs.simpleapp.root.RootNode.Routing.Child2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<Routing> = BackStack(
        initialElement = Child1,
        savedStateMap = buildContext.savedStateMap
    )
) : ParentNode<Routing>(
    buildContext = buildContext,
    routingSource = backStack
) {

    sealed class Routing : Parcelable {
        @Parcelize
        object Child1 : Routing()

        @Parcelize
        object Child2 : Routing()
    }

    override fun resolve(routing: Routing, buildContext: BuildContext): Node =
        when (routing) {
            is Child1 -> ChildNode1(buildContext)
            is Child2 -> ChildNode2(buildContext, ::swapChildren)
        }

    init {
        initAnimation()
    }

    private fun initAnimation() {
        lifecycle.coroutineScope.launch {
            while (true) {
                delay(2000)
                swapChildren()
            }
        }
    }

    private fun swapChildren() {
        if (backStack.activeRouting == Child1) {
            backStack.push(Child2)
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
