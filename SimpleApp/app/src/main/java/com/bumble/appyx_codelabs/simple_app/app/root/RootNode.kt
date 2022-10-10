package com.bumble.appyx_codelabs.simple_app.app.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx_codelabs.simple_app.app.R

class RootNode(
    buildContext: BuildContext,
    // TODO: (4.1) Add the back stack
//    private val backStack: BackStack<NavTarget> = BackStack(
//        initialElement = Child1,
//        savedStateMap = buildContext.savedStateMap
//    ),
) : Node(buildContext) {
    // TODO: (2) Change RootNode to inherit from ParentNode, we'll provide the navModel later
    // : ParentNode<RootNode.NavTarget>(
    // navModel = TODO(),
    // buildContext = buildContext
    // )

    // TODO: (1) Model our destinations using a sealed class that inherits NavTarget
//    sealed class NavTarget {
//        object Child1 : NavTarget()
//        object Child2 : NavTarget()
//    }
    // TODO: (3) Implement the resolve function and add simple children
//    override fun resolve(navTarget: NavTarget, buildContext: BuildContext) =
//        when (navTarget) {
//            is Child1 -> node(buildContext) { Text(text = "Placeholder for child 1") }
//            is Child2 -> node(buildContext) { Text(text = "Placeholder for child 2") }
//        }

    // TODO: (6.3) Use the newly created child nodes, we'll manage navigation in the final step
//    override fun resolve(navTarget: NavTarget, buildContext: BuildContext) =
//        when (navTarget) {
//            is Child1 -> Child1Node(buildContext) { TODO() }
//            is Child2 -> Child2Node(buildContext)
//        }

    // TODO: (7) Push Child2Node when pressing the button

    @Composable
    override fun View(modifier: Modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Appyx Logo",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Hello Appyx!",
                fontSize = 36.sp,
                color = MaterialTheme.colors.onBackground
            )
            // TODO: (4.2) Add the Children and pass the backstack
//            Children(
//                navModel = backStack,
//                modifier = Modifier.fillMaxSize()
//            ) {
//                children<NavTarget> { child ->
//                    child()
//                }
//            }
            // TODO: (5) Add fade in/out transitions
        }
    }
}

