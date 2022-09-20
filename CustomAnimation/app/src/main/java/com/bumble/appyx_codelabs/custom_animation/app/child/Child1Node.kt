package com.bumble.appyx_codelabs.custom_animation.app.child

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node


class Child1Node(buildContext: BuildContext, private val onButtonPressed: () -> Unit) :
    Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.Cyan)
        ) {
            Button(onClick = { onButtonPressed() }, modifier = modifier.align(Alignment.Center)) {
                Text(text = "Press here to navigate")
            }
        }
    }
}
