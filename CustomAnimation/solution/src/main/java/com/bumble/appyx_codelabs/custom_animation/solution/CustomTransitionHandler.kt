package com.bumble.appyx_codelabs.custom_animation.solution

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import com.bumble.appyx.core.navigation.transition.ModifierTransitionHandler
import com.bumble.appyx.core.navigation.transition.TransitionDescriptor
import com.bumble.appyx.core.navigation.transition.TransitionSpec
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.BackStackOperation
import com.bumble.appyx.navmodel.backstack.operation.Pop
import kotlin.math.roundToInt

class CustomTransitionHandler<NavTarget>(
    private val transitionSpec: TransitionSpec<BackStack.State, Offset>
) : ModifierTransitionHandler<NavTarget, BackStack.State>() {

    override fun createModifier(
        modifier: Modifier,
        transition: Transition<BackStack.State>,
        descriptor: TransitionDescriptor<NavTarget, BackStack.State>
    ): Modifier = modifier.composed {

        val scale by transition.animateFloat(
            transitionSpec = { tween(1000) },
            targetValueByState = { it.targetScale(descriptor) },
            label = ""
        )

        val offset by transition.animateOffset(
            transitionSpec = transitionSpec,
            targetValueByState = {
                val height = descriptor.params.bounds.height.value
                it.targetOffset(height)
            },
            label = ""
        )

        val alpha by transition.animateFloat(
            transitionSpec = { tween(1000) },
            targetValueByState = { it.targetAlpha() },
            label = ""
        )

        this
            .scale(scale)
            .offset {
                IntOffset(
                    x = (offset.x * density).roundToInt(),
                    y = (offset.y * density).roundToInt()
                )
            }
            .alpha(alpha)
    }

    private fun fromBottom(height: Float) = Offset(0f, 2f * height)

    private fun BackStack.State.targetScale(descriptor: TransitionDescriptor<NavTarget, BackStack.State>): Float =
        when (this) {
            BackStack.State.CREATED -> 0.4f
            BackStack.State.STASHED -> 0.6f
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

    private fun BackStack.State.targetOffset(height: Float): Offset =
        when (this) {
            BackStack.State.CREATED -> fromBottom(height)
            BackStack.State.ACTIVE,
            BackStack.State.STASHED,
            BackStack.State.DESTROYED -> Offset.Zero
        }

    private fun BackStack.State.targetAlpha(): Float =
        when (this) {
            BackStack.State.CREATED,
            BackStack.State.ACTIVE -> 1f
            else -> 0f
        }
}

@Composable
fun <R> rememberCustomTransitionHandler(
    transitionSpec: TransitionSpec<BackStack.State, Offset> = { spring(stiffness = Spring.StiffnessVeryLow) }
): ModifierTransitionHandler<R, BackStack.State> =
    remember {
        CustomTransitionHandler(transitionSpec = transitionSpec)
    }

