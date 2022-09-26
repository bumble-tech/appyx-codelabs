package com.solution.helloappyx

import android.os.Bundle
import androidx.activity.compose.setContent
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity
import com.solution.helloappyx.ui.theme.HelloAppyxTheme

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloAppyxTheme {
                NodeHost(integrationPoint = integrationPoint) {
                    RootNode(it)
                }
            }
        }
    }
}