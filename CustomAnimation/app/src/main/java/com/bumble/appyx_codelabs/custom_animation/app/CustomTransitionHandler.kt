package com.bumble.appyx_codelabs.custom_animation.app

import androidx.compose.animation.core.Transition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.navigation.transition.ModifierTransitionHandler
import com.bumble.appyx.core.navigation.transition.TransitionDescriptor
import com.bumble.appyx.navmodel.backstack.BackStack

class CustomTransitionHandler<R> : ModifierTransitionHandler<R, BackStack.State>() {

    override fun createModifier(
        modifier: Modifier,
        transition: Transition<BackStack.State>,
        descriptor: TransitionDescriptor<R, BackStack.State>
    ): Modifier {
        TODO("Implement me")
    }
}

@Composable
fun <R> rememberCustomTransitionHandler(): ModifierTransitionHandler<R, BackStack.State> =
    remember {
        CustomTransitionHandler()
    }
