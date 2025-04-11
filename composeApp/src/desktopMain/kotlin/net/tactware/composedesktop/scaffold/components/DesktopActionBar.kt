package net.tactware.composedesktop.scaffold.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
 * Custom implementation of the action bar for context-specific actions in desktop applications.
 * This component is designed to be placed below the top bar and provide context-specific actions
 * like "New Mail" button in Outlook.
 *
 * @param primaryAction Primary action (e.g., "New Mail")
 * @param actions Other actions
 * @param backgroundColor Background color of the action bar
 * @param contentColor Content color of the action bar
 * @param height Height of the action bar
 * @param elevation Elevation of the action bar
 * @param modifier Modifier for the action bar
 */
@Composable
fun DesktopActionBar(
    expansionAction : @Composable () -> Unit = {},
    primaryAction: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    footer: @Composable () -> Unit = {},
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    height: Dp = 56.dp,
    elevation: Dp = 2.dp,
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
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        ) {
            // Expansion action section
            expansionAction()
            // Primary action section
            primaryAction()

            // Other actions section
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()).weight(1f).padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                content = actions
            )

            // Footer section
            footer()
        }
    }
}
