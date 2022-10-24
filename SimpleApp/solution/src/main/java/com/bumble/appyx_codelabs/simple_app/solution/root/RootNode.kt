package com.bumble.appyx_codelabs.simple_app.solution.root

import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackFader
import com.bumble.appyx_codelabs.simple_app.solution.R
import com.bumble.appyx_codelabs.simple_app.solution.child.Child1Node
import com.bumble.appyx_codelabs.simple_app.solution.child.Child2Node
import com.bumble.appyx_codelabs.simple_app.solution.root.RootNode.NavTarget.Child1
import com.bumble.appyx_codelabs.simple_app.solution.root.RootNode.NavTarget.Child2
import com.bumble.appyx_codelabs.simple_app.solution.ui.theme.appyx_bright
import com.bumble.appyx_codelabs.simple_app.solution.ui.theme.appyx_dark


class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        initialElement = Child1,
        savedStateMap = buildContext.savedStateMap
    ),
) : ParentNode<RootNode.NavTarget>(
    navModel = backStack,
    buildContext = buildContext
) {

    sealed class NavTarget {
        object Child1 : NavTarget()
        object Child2 : NavTarget()
    }

    override fun resolve(
        navTarget: NavTarget,
        buildContext: BuildContext
    ) =
        when (navTarget) {
            is Child1 -> Child1Node(buildContext) { backStack.push(Child2) }
            is Child2 -> Child2Node(buildContext)
        }

    @Composable
    override fun View(modifier: Modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .background(color = if (isSystemInDarkTheme()) appyx_bright else appyx_dark)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Appyx Logo",
                    modifier = Modifier.padding(16.dp)
                )
            }
            Children(
                navModel = backStack,
                transitionHandler = rememberBackstackFader(transitionSpec = { spring() }),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
