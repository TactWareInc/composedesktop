package net.tactware.composedesktop.scaffold.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AnimatedCard(
    expanded: Boolean,
    modifier: Modifier = Modifier,
    expansionDuration: Int = 400,
    contentFadeDuration: Int = 300,
    content: @Composable () -> Unit
) {
    // Controls whether the slot content is visible
    var contentVisible by remember { mutableStateOf(false) }

    var cardExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(expanded) {
        if (expanded) {
            cardExpanded = true
        } else {
            // Delay the shrinking of the card until the content is faded out
            delay(2000)
            cardExpanded = false
        }
    }

    LaunchedEffect(expanded) {
        if (expanded) {
            // Delay the expansion of the card until the content is faded in
            delay(expansionDuration.toLong())
            contentVisible = true
        } else {
            // Fade out content before shrinking
            contentVisible = false
        }
    }


//    // Coordinate sequential animations using a coroutine
//    LaunchedEffect(expanded) {
//        if (expanded) {
//            cardExpanded = true
//            delay(expansionDuration.toLong())
//            // Now fade in content
//            contentVisible = true
//        } else {
//            // Fade out content before shrinking
//            contentVisible = false
//            delay(contentFadeDuration.toLong() +expansionDuration.toLong())
//            cardExpanded = false
//        }
//    }

    // Outer AnimatedVisibility for the card itself
    AnimatedVisibility(
        // Keep the card visible if expanded *or* we're in the middle of fading out its content
        visible = cardExpanded,
        enter = slideInHorizontally (
            animationSpec = tween(durationMillis = expansionDuration),
        ),
        exit = slideOutHorizontally(
            animationSpec = tween(durationMillis = expansionDuration)
        ),
        modifier = modifier
    ) {
        Card(
            modifier = Modifier.fillMaxSize()
        ) {
            // Inner AnimatedVisibility for content fade
            AnimatedVisibility(
                visible = contentVisible,
                enter = fadeIn(animationSpec = tween(durationMillis = contentFadeDuration)),
                exit = fadeOut(animationSpec = tween(durationMillis = contentFadeDuration))
            ) {
                // Slot content
                content()
            }
        }
    }
}
