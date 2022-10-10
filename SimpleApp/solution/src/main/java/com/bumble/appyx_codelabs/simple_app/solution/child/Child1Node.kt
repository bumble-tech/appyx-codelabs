package com.bumble.appyx_codelabs.simple_app.solution.child

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx_codelabs.simple_app.solution.ui.theme.appyx_dark
import com.bumble.appyx_codelabs.simple_app.solution.ui.theme.appyx_yellow1


class Child1Node(
    buildContext: BuildContext,
    private val onButtonPressed: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background),
            ) {
                Text(
                    text = "Child 1",
                    fontSize = 36.sp,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Button(
                    onClick = { onButtonPressed() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = appyx_yellow1,
                        contentColor = appyx_dark
                    )
                ) {
                    Text(text = "Press here to navigate")
                }
            }
        }
    }
}

