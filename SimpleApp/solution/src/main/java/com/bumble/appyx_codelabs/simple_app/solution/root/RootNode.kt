package com.bumble.appyx_codelabs.simple_app.solution.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.operation.push
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxComponent
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.ParentNode
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
        model = BackStackModel(
            initialTarget = Child1,
            savedStateMap = buildContext.savedStateMap
        ),
        motionController = { BackStackFader(it) },
    ),
) : ParentNode<RootNode.NavTarget>(
    appyxComponent = backStack,
    buildContext = buildContext
) {

    sealed class NavTarget {
        object Child1 : NavTarget()
        object Child2 : NavTarget()
    }

    override fun resolve(interactionTarget: NavTarget, buildContext: BuildContext): Node =
        when (interactionTarget) {
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
            AppyxComponent(
                appyxComponent = backStack,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
