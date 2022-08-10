package com.bumble.appyx_codelabs.simpleapp

import android.os.Bundle
import androidx.activity.compose.setContent
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity
import com.bumble.appyx_codelabs.simpleapp.root.RootNode
import com.bumble.appyx_codelabs.simpleapp.ui.theme.SimpleAppTheme

class MainActivity : NodeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleAppTheme {
                NodeHost(integrationPoint = integrationPoint) {
                    RootNode(buildContext = it)
                }
            }
        }
    }
}
