package com.bumble.appyx_codelabs.simpleapp.solution.node.root

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx_codelabs.simpleapp.solution.ui.theme.SimpleAppTheme

class RootNode(buildContext: BuildContext) : Node(
    buildContext = buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        Greeting("Appyx")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleAppTheme {
        Greeting("Appyx")
    }
}
