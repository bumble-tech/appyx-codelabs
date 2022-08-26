package com.bumble.appyx_codelabs.simple_app.solution.root

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx_codelabs.simple_app.solution.ui.theme.SimpleAppTheme

class RootNode(
    buildContext: BuildContext,
    private val name: String,
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        Greeting(name = name)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Welcome to Appyx, $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleAppTheme {
        Greeting("Android")
    }
}
