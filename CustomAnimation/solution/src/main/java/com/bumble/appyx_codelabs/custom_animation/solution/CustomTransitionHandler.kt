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
import kotlin.math.roundToInt

class CustomTransitionHandler<NavTarget>(
    private val transitionSpec: TransitionSpec<BackStack.State, Offset>
) : ModifierTransitionHandler<NavTarget, BackStack.State>() {

    private data class Props(
        val scale: Float,
        val offset: Offset,
        val alpha: Float
    )

    override fun createModifier(
        modifier: Modifier,
        transition: Transition<BackStack.State>,
        descriptor: TransitionDescriptor<NavTarget, BackStack.State>
    ): Modifier = modifier.composed {

        val scale by transition.animateFloat(
            transitionSpec = { tween(1000) },
            targetValueByState = { it.targetProps(descriptor).scale },
            label = ""
        )

        val offset by transition.animateOffset(
            transitionSpec = transitionSpec,
            targetValueByState = { it.targetProps(descriptor).offset },
            label = ""
        )

        val alpha by transition.animateFloat(
            transitionSpec = { tween(1000) },
            targetValueByState = { it.targetProps(descriptor).alpha },
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

    private fun BackStack.State.targetProps(
        descriptor: TransitionDescriptor<NavTarget, BackStack.State>,
    ): Props =
        when (this) {
            BackStack.State.CREATED -> Props(
                alpha = 1f,
                scale = 0.4f,
                offset = fromBottom(descriptor.params.bounds.height.value)
            )
            BackStack.State.ACTIVE -> Props(
                alpha = 1f,
                scale = 1f,
                offset = Offset.Zero
            )
            BackStack.State.STASHED -> Props(
                alpha = 0f,
                scale = 0.6f,
                offset = Offset.Zero
            )
            BackStack.State.DESTROYED -> {
                Props(
                    alpha = 0f,
                    scale = 1.25f,
                    offset = Offset.Zero
                )
            }
        }
}

@Composable
fun <R> rememberCustomTransitionHandler(
    transitionSpec: TransitionSpec<BackStack.State, Offset> = { spring(stiffness = Spring.StiffnessVeryLow) }
): ModifierTransitionHandler<R, BackStack.State> =
    remember {
        CustomTransitionHandler(transitionSpec = transitionSpec)
    }

