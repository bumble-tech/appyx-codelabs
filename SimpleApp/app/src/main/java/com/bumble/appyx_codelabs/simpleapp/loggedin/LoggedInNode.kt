package com.bumble.appyx_codelabs.simpleapp.loggedin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx_codelabs.simpleapp.ui.theme.sizzling_red

class LoggedInNode(buildContext: BuildContext) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(color = sizzling_red)
                .fillMaxSize()
        ) {
            Text(
                text = "This is Child 1",
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
        }
    }
}
