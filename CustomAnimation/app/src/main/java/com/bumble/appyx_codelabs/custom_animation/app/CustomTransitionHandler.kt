package com.bumble.appyx_codelabs.custom_animation.app

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import com.bumble.appyx.core.navigation.transition.ModifierTransitionHandler
import com.bumble.appyx.core.navigation.transition.TransitionDescriptor
import com.bumble.appyx.core.navigation.transition.TransitionSpec
import com.bumble.appyx.navmodel.backstack.BackStack

class CustomTransitionHandler<NavTarget>(
    private val offsetSpec: TransitionSpec<BackStack.State, Offset>,
    private val floatSpec: TransitionSpec<BackStack.State, Float>,
) : ModifierTransitionHandler<NavTarget, BackStack.State>() {

    // TODO: (1) Define properties to animate

    // TODO: (2) Define some actual values representing our key states

    override fun createModifier(
        modifier: Modifier,
        transition: Transition<BackStack.State>,
        descriptor: TransitionDescriptor<NavTarget, BackStack.State>
    ): Modifier = modifier.composed {
        // TODO: (4) Define a fading animation

        // TODO: (6) Define the slide animation

        // TODO: (7) Define the exploding animation

        this
    }

    // TODO: (3) Define the mapping between the current state and the UI properties
}

@Composable
fun <NavTarget> rememberCustomTransitionHandler(
    offsetSpec: TransitionSpec<BackStack.State, Offset> = { spring(stiffness = Spring.StiffnessVeryLow) },
    floatSpec: TransitionSpec<BackStack.State, Float> = { spring(stiffness = Spring.StiffnessVeryLow) }
): ModifierTransitionHandler<NavTarget, BackStack.State> =
    remember {
        CustomTransitionHandler(
            offsetSpec = offsetSpec,
            floatSpec = floatSpec
        )
    }
