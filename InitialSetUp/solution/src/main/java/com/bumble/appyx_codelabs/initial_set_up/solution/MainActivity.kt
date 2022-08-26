package com.bumble.appyx_codelabs.initial_set_up.solution

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity
import com.bumble.appyx_codelabs.initial_set_up.solution.root.RootNode
import com.bumble.appyx_codelabs.initial_set_up.solution.ui.theme.InitialSetUpTheme

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitialSetUpTheme {
                NodeHost(integrationPoint = integrationPoint) { buildContext ->
                    RootNode(buildContext)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InitialSetUpTheme {
        Greeting("Android")
    }
}
