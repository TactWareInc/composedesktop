package net.tactware.composedesktop.scaffold.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.TextField
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SmallTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides  Dp.Unspecified) {
        TextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = modifier,
            textStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal),
            singleLine = true,
            shape = RoundedCornerShape(8.dp)
        )
    }
}