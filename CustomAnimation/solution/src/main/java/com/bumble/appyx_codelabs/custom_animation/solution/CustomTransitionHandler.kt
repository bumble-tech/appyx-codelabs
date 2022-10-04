package com.bumble.appyx_codelabs.custom_animation.solution

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import com.bumble.appyx.core.navigation.transition.ModifierTransitionHandler
import com.bumble.appyx.core.navigation.transition.TransitionDescriptor
import com.bumble.appyx.core.navigation.transition.TransitionSpec
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.BackStackOperation
import com.bumble.appyx.navmodel.backstack.operation.Pop

class CustomTransitionHandler<NavTarget>(
    private val transitionSpec: TransitionSpec<BackStack.State, Float> = {
        tween(durationMillis = 1000)
    },
) : ModifierTransitionHandler<NavTarget, BackStack.State>() {

    override fun createModifier(
        modifier: Modifier,
        transition: Transition<BackStack.State>,
        descriptor: TransitionDescriptor<NavTarget, BackStack.State>
    ): Modifier = modifier.composed {
        val scale by transition.animateFloat(
            transitionSpec = transitionSpec,
            targetValueByState = { it.targetScale(descriptor) },
            label = ""
        )

        val alpha by transition.animateFloat(
            transitionSpec = transitionSpec,
            targetValueByState = { it.targetAlpha() },
            label = ""
        )

        val rotation by transition.animateFloat(
            transitionSpec = transitionSpec,
            targetValueByState = { it.targetRotation() },
            label = ""
        )

        return@composed this
            .scale(scale)
            .alpha(alpha)
            .graphicsLayer { rotationZ = rotation }
    }

    private fun BackStack.State.targetScale(descriptor: TransitionDescriptor<NavTarget, BackStack.State>): Float =
        when (this) {
            BackStack.State.CREATED -> 0.2f
            BackStack.State.STASHED -> 0.5f
            BackStack.State.ACTIVE -> 1f
            BackStack.State.DESTROYED -> {
                val operation = descriptor.operation as? BackStackOperation
                if (operation is Pop) {
                    1.25f
                } else {
                    1f
                }
            }
        }

    private fun BackStack.State.targetAlpha(): Float =
        when (this) {
            BackStack.State.CREATED,
            BackStack.State.ACTIVE -> 1f
            else -> 0f
        }

    private fun BackStack.State.targetRotation(): Float =
        when (this) {
            BackStack.State.CREATED -> 180f
            else -> 0f
        }
}

@Composable
fun <R> rememberCustomTransitionHandler(
    transitionSpec: TransitionSpec<BackStack.State, Float> = { spring(stiffness = Spring.StiffnessVeryLow) }
): ModifierTransitionHandler<R, BackStack.State> =
    remember {
        CustomTransitionHandler(transitionSpec = transitionSpec)
    }

