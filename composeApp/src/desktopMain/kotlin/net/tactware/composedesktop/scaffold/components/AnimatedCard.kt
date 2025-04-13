package net.tactware.composedesktop.scaffold.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AnimatedCard(
    showContent: Boolean,
    contentFadeDuration: Int = 300,
    modifier: Modifier = Modifier,
    enter : EnterTransition = fadeIn(animationSpec = tween(durationMillis = contentFadeDuration)),
    exit : ExitTransition = fadeOut(animationSpec = tween(durationMillis = contentFadeDuration)),
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
    ) {
        // Inner AnimatedVisibility for content fade
        AnimatedVisibility(
            visible = showContent,
            enter = enter,
            exit = exit
        ) {
            // Slot content
            content()
        }
    }
}
