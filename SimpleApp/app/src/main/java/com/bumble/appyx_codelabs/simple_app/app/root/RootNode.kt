package com.bumble.appyx_codelabs.simple_app.app.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx_codelabs.simple_app.app.R

class RootNode(
    buildContext: BuildContext,
    // TODO: (4.1) Add the back stack
) : Node(buildContext) {
    // TODO: (2) Change RootNode to inherit from ParentNode, we'll provide the navModel later

    // TODO: (1) Model our destinations using a sealed class that inherits NavTarget

    // TODO: (3) Implement the resolve function and add simple children

    // TODO: (6.3) Use the newly created child nodes, we'll manage navigation in the final step

    // TODO: (7) Push Child2Node when pressing the button

    @Composable
    override fun View(modifier: Modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Appyx Logo",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Hello Appyx!",
                fontSize = 36.sp,
                color = MaterialTheme.colors.onBackground
            )
            // TODO: (4.2) Add the Children and pass the backstack

            // TODO: (5) Add fade in/out transitions
        }
    }
}

