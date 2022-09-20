package com.bumble.appyx_codelabs.simple_app.solution.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackSlider
import com.bumble.appyx_codelabs.simple_app.solution.child.Child1Node
import com.bumble.appyx_codelabs.simple_app.solution.child.Child2Node
import com.bumble.appyx_codelabs.simple_app.solution.root.RootNode.Routing.Child1
import com.bumble.appyx_codelabs.simple_app.solution.root.RootNode.Routing.Child2


class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<Routing> = BackStack(Child1, null),
) : ParentNode<RootNode.Routing>(navModel = backStack, buildContext) {

    sealed class Routing {
        object Child1 : Routing()
        object Child2 : Routing()
    }

    override fun resolve(routing: Routing, buildContext: BuildContext) =
        when (routing) {
            is Child1 -> Child1Node(buildContext) { backStack.push(Child2) }
            is Child2 -> Child2Node(buildContext)
        }

    @Composable
    override fun View(modifier: Modifier) {
        Children(
            navModel = backStack,
            transitionHandler = rememberBackstackSlider(),
        ) {
            children<Routing> { child ->
                child()
            }
        }
    }
}