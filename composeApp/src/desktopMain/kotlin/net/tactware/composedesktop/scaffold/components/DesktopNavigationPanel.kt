package net.tactware.composedesktop.scaffold.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import net.tactware.composedesktop.scaffold.state.NavigationPanelState
import net.tactware.composedesktop.scaffold.state.PanelState

/**
 * Custom implementation of the expandable navigation panel optimized for desktop applications.
 * This component is designed to be placed next to the navigation rail and provide
 * detailed navigation options like folder structure.
 *
 * @param state State controller for the navigation panel
 * @param header Content to be displayed at the top of the panel
 * @param content Content to be displayed in the middle of the panel
 * @param footer Content to be displayed at the bottom of the panel
 * @param backgroundColor Background color of the panel
 * @param contentColor Content color of the panel
 * @param expandedWidth Width of the panel when expanded
 * @param collapsedWidth Width of the panel when collapsed
 * @param showToggleButton Whether to show the toggle button at the bottom
 * @param modifier Modifier for the panel
 */
@Composable
fun DesktopPanel(
    // Header content
    header: @Composable () -> Unit = {},

    // Content
    content: @Composable ColumnScope.() -> Unit,

    // Footer content
    footer: @Composable ColumnScope.() -> Unit = {},

    // Background color
    backgroundColor: Color = MaterialTheme.colorScheme.surface,

    // Content color
    contentColor: Color = contentColorFor(backgroundColor),

    // Modifier
    modifier: Modifier = Modifier
) {


    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        modifier = modifier.fillMaxHeight()
    ) {
        Column(
            modifier = Modifier.fillMaxHeight().padding(vertical = 8.dp)
        ) {
            // Header section
            header()

            // Main content section
            Column(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                content = content
            )

            // Footer section with custom footer content
            footer()
        }
    }
}
