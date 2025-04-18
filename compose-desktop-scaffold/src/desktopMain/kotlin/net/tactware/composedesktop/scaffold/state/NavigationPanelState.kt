package net.tactware.composedesktop.scaffold.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/**
 * Manages the state of the expandable navigation panel.
 *
 * @param initialExpanded Whether the panel should be initially expanded.
 */
class NavigationPanelState(
    initialExpanded: Boolean = false
) {
    // State for panel expansion
    var isExpanded by mutableStateOf(initialExpanded)
        private set

    // Function to toggle panel state
    fun toggle() {
        isExpanded = !isExpanded
    }

    // Function to expand panel
    fun expand() {
        isExpanded = true
    }

    // Function to collapse panel
    fun collapse() {
        isExpanded = false
    }
}

/**
 * Composable function to create and remember NavigationPanelState.
 *
 * @param initialExpanded Whether the panel should be initially expanded.
 * @return A remembered [NavigationPanelState] instance.
 */
@Composable
fun rememberNavigationPanelState(
    initialExpanded: Boolean = false
): NavigationPanelState = remember { NavigationPanelState(initialExpanded) }
