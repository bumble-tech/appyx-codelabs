package com.bumble.appyx_codelabs.custom_animation.solution

import androidx.compose.animation.core.Transition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.navigation.transition.ModifierTransitionHandler
import com.bumble.appyx.core.navigation.transition.TransitionDescriptor
import com.bumble.appyx.navmodel.backstack.BackStack

class CustomTransitionHandler<R> : ModifierTransitionHandler<R, BackStack.TransitionState>() {

    override fun createModifier(
        modifier: Modifier,
        transition: Transition<BackStack.TransitionState>,
        descriptor: TransitionDescriptor<R, BackStack.TransitionState>
    ): Modifier {
        TODO("Not yet implemented")
    }
}

@Composable
fun <R> rememberCustomTransitionHandler(): ModifierTransitionHandler<R, BackStack.TransitionState> =
    remember {
        CustomTransitionHandler()
    }
