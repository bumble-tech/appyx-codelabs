package com.bumble.appyx_codelabs.simpleapp.solution

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity
import com.bumble.appyx_codelabs.simpleapp.solution.node.root.RootNode
import com.bumble.appyx_codelabs.simpleapp.solution.ui.theme.SimpleAppTheme

class MainActivity : NodeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NodeHost(integrationPoint = integrationPoint) {
                        RootNode(it)
                    }
                }
            }
        }
    }
}
