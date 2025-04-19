package net.tactware.composedesktop.scaffold.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Custom implementation of the navigation rail optimized for desktop applications.
 * This component is designed to be placed on the far left of the screen and provide
 * quick access to main application sections.
 *
 * @param header Content to be displayed at the top of the rail
 * @param footer Content to be displayed at the bottom of the rail
 * @param content Content to be displayed in the middle of the rail (navigation items)
 * @param backgroundColor Background color of the rail
 * @param contentColor Content color of the rail
 * @param width Width of the rail
 * @param modifier Modifier for the rail
 */
@Composable
fun DesktopNavigationRail(
    header: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    width: Dp = 56.dp,
    modifier: Modifier = Modifier
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        modifier = modifier.width(width).fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight().padding(vertical = 8.dp)
        ) {
            // Header section
            header()

            // Main content section (navigation items)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f),
                content = content
            )

            // Footer section
            footer()
        }
    }
}
