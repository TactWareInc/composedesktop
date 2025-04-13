package net.tactware.composedesktop.scaffold.components

import androidx.compose.runtime.Composable

/**
 * Defines a navigation item for use in UI navigation structures such as drawers or tabs.
 * Each navigation item includes a title, an icon, a destination, and a priority level
 * to determine its placement or visibility relative to other navigation items.
 */
interface NavigationItem {
    /**
     * The title of the navigation item. This is typically displayed as the label
     * accompanying the icon in navigation menus.
     */
    val title: String

    /**
     * A composable function that provides the icon associated with the navigation item.
     * This icon is displayed in navigation menus to visually represent the item.
     */
    val icon: @Composable () -> Unit
}
