package com.bumble.appyx_codelabs.custom_animation.solution

import android.os.Bundle
import androidx.activity.compose.setContent
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity
import com.bumble.appyx_codelabs.custom_animation.solution.root.RootNode
import com.bumble.appyx_codelabs.custom_animation.solution.ui.theme.CustomAnimationTheme

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomAnimationTheme {
                NodeHost(integrationPoint = integrationPoint) {
                    RootNode(it)
                }
            }
        }
    }
}
