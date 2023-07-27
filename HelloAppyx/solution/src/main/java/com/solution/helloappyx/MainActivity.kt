package com.solution.helloappyx

import android.os.Bundle
import androidx.activity.compose.setContent
import com.bumble.appyx.navigation.integration.NodeHost
import com.bumble.appyx.navigation.integrationpoint.NodeActivity
import com.solution.helloappyx.ui.theme.HelloAppyxTheme

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloAppyxTheme {
                NodeHost(integrationPoint = appyxV2IntegrationPoint) {
                    RootNode(it)
                }
            }
        }
    }
}
