package net.tactware.composedesktop.scaffold.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A desktop-oriented scaffold that implements the slot pattern similar to Material 3's Scaffold
 * but optimized for desktop applications with navigation rail, expandable panel, and action bar.
 *
 * @param topBar Slot for the top bar containing search and global actions
 * @param navigationRail Slot for the navigation rail on the far left
 * @param content Slot for the main content area
 * @param modifier Modifier for the entire scaffold
 */
@Composable
fun DesktopApplicationScaffold(
    topBar: @Composable () -> Unit,
    navigationRail: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        // Top bar area
        topBar()

        // Main content row with navigation and content
        Row(modifier = Modifier.fillMaxSize()) {
            // Navigation rail (always visible)
            navigationRail()

            Box(Modifier.weight(1f).fillMaxHeight()) {
                content()
            }
        }
    }
}
