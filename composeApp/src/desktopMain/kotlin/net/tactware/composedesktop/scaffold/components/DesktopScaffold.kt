package net.tactware.composedesktop.scaffold.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
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
import androidx.compose.ui.unit.dp
import net.tactware.composedesktop.scaffold.state.NavigationPanelState
import net.tactware.composedesktop.scaffold.state.PanelState
import net.tactware.composedesktop.scaffold.state.rememberNavigationPanelState

/**
 * A desktop-oriented scaffold that implements the slot pattern similar to Material 3's Scaffold
 * but optimized for desktop applications with navigation rail, expandable panel, and action bar.
 *
 * @param topBar Slot for the top bar containing search and global actions
 * @param navigationRail Slot for the navigation rail on the far left
 * @param navigationPanel Slot for the expandable navigation panel
 * @param actionBar Slot for the action bar containing context-specific actions
 * @param content Slot for the main content area
 * @param navigationPanelState State controller for the navigation panel
 * @param modifier Modifier for the entire scaffold
 */
@Composable
fun DesktopScaffold(
    // Slot for the top bar (required)
    topBar: @Composable () -> Unit,

    // Slot for the navigation rail (required)
    navigationRail: @Composable () -> Unit,

    // Slot for the navigation panel (optional with default empty implementation)
    navigationPanel: @Composable (PanelState) -> Unit = {},

    // Slot for the action bar (required) - contains context actions like "New Mail"
    actionBar: @Composable () -> Unit,

    // Slot for the main content (required)
    content: @Composable () -> Unit,

    // Navigation panel state (expanded/collapsed)
    navigationPanelState: NavigationPanelState = rememberNavigationPanelState(),

    // Modifier for the entire scaffold
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        // Top bar area
        topBar()

        // Action bar area
        actionBar()

        // Main content row with navigation and content
        Row(modifier = Modifier.fillMaxSize()) {
            // Navigation rail (always visible)
            navigationRail()

            // Navigation panel (expandable)
            val panelWidth by animateDpAsState(
                targetValue = if (navigationPanelState.isExpanded) 240.dp else 0.dp,
                animationSpec = tween(durationMillis = 300),
                label = "Navigation Panel Width"
            )

            val animationProgress = if (navigationPanelState.isExpanded) {
                if (panelWidth.value == 240f) 1f else panelWidth.value / 240f
            } else {
                if (panelWidth.value == 0f) 0f else panelWidth.value / 240f
            }

            val panelState = if (navigationPanelState.isExpanded) {
                PanelState.Expanded(animationProgress)
            } else {
                PanelState.Collapsed(animationProgress)
            }

            AnimatedCard(navigationPanelState.isExpanded, Modifier.width(panelWidth).padding(4.dp)) {
                Box(modifier = Modifier.width(panelWidth).fillMaxHeight()) {
                    navigationPanel(panelState)
                }
            }

            // Main content area
            Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                Card(modifier = Modifier.fillMaxSize().padding(4.dp)) {
                    content()

                }
            }
        }
    }
}
