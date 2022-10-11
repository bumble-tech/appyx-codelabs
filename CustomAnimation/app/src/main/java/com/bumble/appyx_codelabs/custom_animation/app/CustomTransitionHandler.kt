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
    private val transitionSpec: TransitionSpec<BackStack.State, Offset>
) : ModifierTransitionHandler<NavTarget, BackStack.State>() {

    // TODO: (1) Define the props we'll animate as a data class

    override fun createModifier(
        modifier: Modifier,
        transition: Transition<BackStack.State>,
        descriptor: TransitionDescriptor<NavTarget, BackStack.State>
    ): Modifier = modifier.composed {
        // TODO: (6.1) Animate the value of scale

        // TODO: (6.2) Animate the value of offset

        // TODO: (6.3) Animate the value of alpha

        this
        // TODO: (6.4) Apply the changes to the current modifier
    }

    // TODO: (2) Define a function that creates the target props

    // TODO: (3) An element in a back stack can be in on of the following states:

    // TODO: (4) Next we'll define the values for each properties when in a given state

    // TODO: (5.1) when a child is created it will be:
        // Fully visible -> alpha 1f
        // Smaller -> scale 0.4f
        // Sliding in from the bottom -> see TODO: (5.2)
    // TODO: (5.3) when a child is active it will be:
        // Fully visible -> alpha 1f
        // Fills it's maximum size -> scale 1f
        // In the centre of the screen -> Offset.Zero
    // TODO: (5.4) when a child is stashed it will be:
        // Invisible -> alpha 0f
        // Becomes smaller -> scale 0.6f
        // In the centre of the screen -> Offset.Zero
    // TODO: (5.5) when a child is destroyed it will be:
        // Invisible -> alpha 0f
        // Creates an exploding effect -> scale 1.25f
        // In the centre of the screen -> Offset.Zero

    // TODO: (5.2) When created the child will be below the visible screen
//    private fun fromBottom(height: Float) = Offset(0f, 2f * height)
}

@Composable
fun <NavTarget> rememberCustomTransitionHandler(
    transitionSpec: TransitionSpec<BackStack.State, Offset> = { spring(stiffness = Spring.StiffnessVeryLow) }
): ModifierTransitionHandler<NavTarget, BackStack.State> =
    remember {
        CustomTransitionHandler(transitionSpec = transitionSpec)
    }
