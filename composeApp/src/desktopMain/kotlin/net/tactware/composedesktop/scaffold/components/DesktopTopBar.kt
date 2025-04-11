package net.tactware.composedesktop.scaffold.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
 * Custom implementation of the top bar optimized for desktop applications.
 * This component is designed to be placed at the top of the screen and provide
 * search functionality and global actions.
 *
 * @param title Title or branding element
 * @param search Search component
 * @param actions Actions on the right side
 * @param backgroundColor Background color of the top bar
 * @param contentColor Content color of the top bar
 * @param elevation Elevation of the top bar
 * @param height Height of the top bar
 * @param modifier Modifier for the top bar
 */
@Composable
fun DesktopTopBar(
    // Title or branding element
    title: @Composable () -> Unit = {},

    // Search component
    search: @Composable () -> Unit = {},

    // Actions on the right side
    actions: @Composable RowScope.() -> Unit = {},

    // Background color
    backgroundColor: Color = MaterialTheme.colorScheme.surface,

    // Content color
    contentColor: Color = contentColorFor(backgroundColor),

    // Elevation
    elevation: Dp = 4.dp,

    // Height of the top bar
    height: Dp = 48.dp,

    // Modifier
    modifier: Modifier = Modifier
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        tonalElevation = elevation,
        modifier = modifier.fillMaxWidth().height(height)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
        ) {
            // Title/branding section
            title()

            // Search section (centered)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f),
                content = { search() }
            )

            // Actions section
            Row(
                verticalAlignment = Alignment.CenterVertically,
                content = actions
            )
        }
    }
}
