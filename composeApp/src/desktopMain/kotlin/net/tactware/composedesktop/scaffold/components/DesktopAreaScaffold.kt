package net.tactware.composedesktop.scaffold.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.tactware.composedesktop.scaffold.state.NavigationPanelState
import net.tactware.composedesktop.scaffold.state.OptionalPanelState
import net.tactware.composedesktop.scaffold.state.PanelState
import net.tactware.composedesktop.scaffold.state.rememberNavigationPanelState

@Composable
fun DesktopAreaScaffold(
    actionBar: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    navigationPanel: @Composable (PanelState) -> Unit = {},
    navigationPanelState: NavigationPanelState = rememberNavigationPanelState(),
    navigationPanelWidth : Dp = 240.dp,
    optionalPanel: (@Composable (PanelState) -> Unit)? = null,
    optionalPanelState: OptionalPanelState? = null,
    optionalPanelWidth : Dp = 240.dp,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        // Action bar area
        actionBar()

        Row {    // Navigation panel (expandable)
            val navPanelWidth by animateDpAsState(
                targetValue = if (navigationPanelState.isExpanded) navigationPanelWidth else 0.dp,
                animationSpec = tween(durationMillis = 300),
                label = "Navigation Panel Width"
            )




            val animationProgress = if (navigationPanelState.isExpanded) {
                if (navPanelWidth.value == 240f) 1f else navPanelWidth.value / 240f
            } else {
                if (navPanelWidth.value == 0f) 0f else navPanelWidth.value / 240f
            }

            val panelState = if (navigationPanelState.isExpanded) {
                PanelState.Expanded(animationProgress)
            } else {
                PanelState.Collapsed(animationProgress)
            }

            AnimatedCard(navigationPanelState.isExpanded, Modifier.width(navPanelWidth).padding(4.dp)) {
                Box(modifier = Modifier.width(navPanelWidth).fillMaxHeight()) {
                    navigationPanel(panelState)
                }
            }

            // Main content area
            Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                Card(modifier = Modifier.fillMaxSize().padding(4.dp)) {
                    content()
                }
            }

            if (optionalPanel != null && optionalPanelState != null) {
                val optPanelWidth by animateDpAsState(
                    targetValue = if (optionalPanelState.isExpanded) optionalPanelWidth else 0.dp,
                    animationSpec = tween(durationMillis = 300),
                    label = "Optional Panel Width"
                )
                AnimatedCard(optionalPanelState.isExpanded, Modifier.width(optPanelWidth).padding(4.dp)) {
                    Box(modifier = Modifier.width(optPanelWidth).fillMaxHeight()) {
                        optionalPanel(panelState)
                    }
                }
            }
        }
    }
}