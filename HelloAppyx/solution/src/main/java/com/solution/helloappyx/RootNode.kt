package com.solution.helloappyx

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.solution.helloappyx.ui.theme.HelloAppyxTheme

class RootNode(buildContext: BuildContext) : Node(buildContext = buildContext) {

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
                contentDescription = "Appyx Logo"
            )
            Greeting("Hello Appyx")
        }
    }
}

@Composable
fun Greeting(message: String) {
    Text(
        text = message,
        style = MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.onBackground)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloAppyxTheme {
        Greeting("Hello Appyx Preview")
    }
}
