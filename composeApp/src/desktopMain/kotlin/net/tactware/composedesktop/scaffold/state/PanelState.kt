package net.tactware.composedesktop.scaffold.state

import androidx.compose.runtime.Stable

/**
 * Sealed interface to represent different states of the navigation panel.
 */
@Stable
sealed interface PanelState {
    /**
     * Expanded state with animation progress.
     */
    @Stable
    data object Expanded : PanelState

    /**
     * Collapsed state with animation progress.
     */
    @Stable
    data object Collapsed : PanelState
}
