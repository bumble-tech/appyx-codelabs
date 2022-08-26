package com.bumble.appyx_codelabs.firststeps.solution

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity
import com.bumble.appyx_codelabs.firststeps.solution.root.RootNode
import com.bumble.appyx_codelabs.firststeps.solution.ui.theme.FirstStepsTheme

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstStepsTheme {
                NodeHost(integrationPoint = integrationPoint){
                    RootNode(it, "Jon Doe")
                }
            }
        }
    }
}
