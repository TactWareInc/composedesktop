package net.tactware.composedesktop.scaffold.example

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

/**
 * Main entry point for the example application.
 */
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Desktop Scaffold Example",
        state = rememberWindowState(size = DpSize(1200.dp, 800.dp))
    ) {
        AppTheme {
            OutlookExample()
        }
    }
}

/**
 * Theme wrapper for the application.
 */
@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        content = content
    )
}
