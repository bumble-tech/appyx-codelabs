package com.bumble.appyx_codelabs.custom_animation.solution.child

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node


class Child2Node(buildContext: BuildContext) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.Magenta)
        ) {
            Text(
                modifier = modifier.align(Alignment.Center),
                text = "Press back to navigate to previous screen"
            )
        }
    }
}