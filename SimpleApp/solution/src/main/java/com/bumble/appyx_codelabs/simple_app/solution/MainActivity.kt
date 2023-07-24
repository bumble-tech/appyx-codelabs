package com.bumble.appyx_codelabs.simple_app.solution

import android.os.Bundle
import androidx.activity.compose.setContent
import com.bumble.appyx.navigation.integration.NodeHost
import com.bumble.appyx.navigation.integrationpoint.NodeActivity
import com.bumble.appyx_codelabs.simple_app.solution.root.RootNode
import com.bumble.appyx_codelabs.simple_app.solution.ui.theme.SimpleAppTheme

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleAppTheme {
                NodeHost(integrationPoint = appyxV2IntegrationPoint) {
                    RootNode(it)
                }
            }
        }
    }
}
