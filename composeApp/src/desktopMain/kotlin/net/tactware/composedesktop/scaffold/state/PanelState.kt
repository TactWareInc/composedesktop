package net.tactware.composedesktop.scaffold.state

import androidx.compose.runtime.Stable

/**
 * Sealed interface to represent different states of the navigation panel.
 */
@Stable
sealed interface PanelState {
    /**
     * Expanded state with animation progress.
     *
     * @param animationProgress The progress of the expansion animation (0.0 to 1.0).
     */
    @Stable
    data class Expanded(val animationProgress: Float) : PanelState

    /**
     * Collapsed state with animation progress.
     *
     * @param animationProgress The progress of the collapse animation (0.0 to 1.0).
     */
    @Stable
    data class Collapsed(val animationProgress: Float) : PanelState
}
