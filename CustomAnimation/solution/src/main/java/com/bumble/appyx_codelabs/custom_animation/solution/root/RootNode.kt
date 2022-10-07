package com.bumble.appyx_codelabs.custom_animation.solution.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx_codelabs.custom_animation.solution.child.ChildNode
import com.bumble.appyx_codelabs.custom_animation.solution.rememberCustomTransitionHandler
import com.bumble.appyx_codelabs.custom_animation.solution.root.RootNode.NavTarget
import com.bumble.appyx_codelabs.custom_animation.solution.root.RootNode.NavTarget.Child
import com.bumble.appyx_codelabs.custom_animation.solution.ui.theme.appyx_dark
import com.bumble.appyx_codelabs.custom_animation.solution.ui.theme.appyx_yellow1


class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(Child(0), null),
) : ParentNode<NavTarget>(navModel = backStack, buildContext) {

    private var totalNodes = 1

    sealed class NavTarget {
        data class Child(val startValue: Int) : NavTarget()
    }

    override fun resolve(navTarget: NavTarget, buildContext: BuildContext) =
        when (navTarget) {
            is Child -> ChildNode(
                buildContext = buildContext,
                startValue = navTarget.startValue
            )
        }

    @Composable
    override fun View(modifier: Modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.9f)
                    .padding(16.dp)
            ) {
                Children(
                    navModel = backStack,
                    transitionHandler = rememberCustomTransitionHandler(),
                ) {
                    children<NavTarget> { child ->
                        child()
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
                    .padding(bottom = 16.dp)
            ) {
                CustomButton(
                    onClick = {
                        totalNodes--
                        backStack.pop()
                    }
                ) {
                    Text("Pop")
                }
                CustomButton(
                    onClick = {
                        totalNodes++
                        backStack.push(Child(startValue = totalNodes - 1))
                    }
                ) {
                    Text("Push")
                }
            }
        }
    }

    @Composable
    private fun CustomButton(
        modifier: Modifier = Modifier,
        onClick: () -> Unit,
        content: @Composable () -> Unit
    ) {
        Button(
            modifier = modifier,
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = appyx_yellow1,
                contentColor = appyx_dark
            )
        ) { content() }
    }
}