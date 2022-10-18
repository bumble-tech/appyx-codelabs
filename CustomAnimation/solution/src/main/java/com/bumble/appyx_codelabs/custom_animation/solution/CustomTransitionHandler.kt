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
        val scale: Float = 1f,
        val offset: Offset = Offset.Zero,
        val alpha: Float = 1f
    )

    private val created = Props()
    private val active = created.copy(alpha = 1f, scale = 1f)
    private val stashed = active.copy(alpha = 0.5f, scale = 0.6f)
    private val destroyed = active.copy(alpha = 0f, scale = 1.25f)

    override fun createModifier(
        modifier: Modifier,
        transition: Transition<BackStack.State>,
        descriptor: TransitionDescriptor<NavTarget, BackStack.State>
    ): Modifier = modifier.composed {

        val alpha by transition.animateFloat(
            transitionSpec = { tween(2000) },
            targetValueByState = { it.targetProps(descriptor).alpha },
            label = ""
        )

        val offset by transition.animateOffset(
            transitionSpec = transitionSpec,
            targetValueByState = { it.targetProps(descriptor).offset },
            label = ""
        )

        val scale by transition.animateFloat(
            transitionSpec = { tween(1000) },
            targetValueByState = { it.targetProps(descriptor).scale },
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
            BackStack.State.CREATED -> created.copy(
                offset = fromBottom(descriptor.params.bounds.height.value)
            )
            BackStack.State.ACTIVE -> active
            BackStack.State.STASHED -> stashed
            BackStack.State.DESTROYED -> destroyed
        }
}

@Composable
fun <R> rememberCustomTransitionHandler(
    transitionSpec: TransitionSpec<BackStack.State, Offset> = { spring(stiffness = Spring.StiffnessVeryLow) }
): ModifierTransitionHandler<R, BackStack.State> =
    remember {
        CustomTransitionHandler(transitionSpec = transitionSpec)
    }
