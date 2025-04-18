package net.tactware.composedesktop.scaffold.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ExpandingDesktopNavigationRail(
    navItems: List<NavigationItem>,
    selectedNavItem: NavigationItem?,
    onItemSelected: (NavigationItem) -> Unit,
    header: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
    isExpanded: Boolean,
    collapsedWidth: Dp = 56.dp,
    expandedWidth: Dp = 200.dp,
    modifier: Modifier = Modifier
) {

    val animatedWidth by animateDpAsState(
        targetValue = if (isExpanded) expandedWidth else collapsedWidth,
        animationSpec = tween(durationMillis = 300),
        label = "RailWidth"
    )

    var showLabels by remember { mutableStateOf(false) }

    // Delay label animation slightly after width animates in
    LaunchedEffect(isExpanded) {
        if (isExpanded) delay(200)
    }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        contentColor = contentColorFor(MaterialTheme.colorScheme.surface),
        modifier = modifier
            .width(animatedWidth)
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            header()

            Column(
                modifier = Modifier.weight(1f).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                navItems.forEach { item ->
                    val isSelected = selectedNavItem == item
                    val backgroundColor by animateColorAsState(
                        targetValue = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
                        animationSpec = tween(durationMillis = 300),
                        label = "Background Color Animation"
                    )

                    val contentColor by animateColorAsState(
                        targetValue = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onPrimary,
                        animationSpec = tween(durationMillis = 300),
                        label = "Background Color Animation"
                    )

                    Surface(
                        modifier = Modifier.fillMaxWidth().padding(4.dp).clickable {
                            onItemSelected(item)
                        },
                        shape = RoundedCornerShape(8.dp),
                        color = backgroundColor,
                        shadowElevation = if (isSelected) 8.dp else 0.dp,
                        contentColor = contentColor
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            item.icon.invoke()

                            AnimatedVisibility(
                                visible = showLabels,
                                enter = expandHorizontally(animationSpec = tween(durationMillis = 400)),
                                exit = shrinkHorizontally(animationSpec = tween(durationMillis = 100))
                            ) {
                                Row {
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        item.title,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
            footer()
        }
    }
}