package net.tactware.composedesktop.scaffold.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun WidthExpandingCard(
    expanded : Boolean,
    width : Dp,
    modifier: Modifier,
    animationTime : Int = 300,
    content : @Composable () -> Unit,

){
    var expandedState by remember { mutableStateOf(expanded) }
    var showContent by remember { mutableStateOf(expanded) }

    LaunchedEffect(expanded) {
        if (expanded) {
            expandedState = true
            delay(animationTime.toLong())
            showContent = true
        }else{
            showContent = false
            delay(animationTime.toLong())
            expandedState = false
        }
    }

    val animatedWidth by animateDpAsState(
        targetValue = if (expandedState) width else 0.dp,
        animationSpec = tween(durationMillis = animationTime),
        label = "Navigation Panel Width"
    )

    AnimatedCard(
        showContent = showContent,
        modifier = modifier.width(animatedWidth),
        contentFadeDuration = animationTime,
    ) {
        content()
    }
}