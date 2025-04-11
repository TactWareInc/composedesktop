package net.tactware.composedesktop.scaffold.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/**
 * A custom TextField component designed specifically for desktop applications.
 * This component has a fixed height of 36.dp to fit within desktop top bars,
 * with proper internal layout to ensure text is fully visible.
 *
 * @param value The input text to be shown in the text field
 * @param onValueChange The callback that is triggered when the input service updates the text
 * @param modifier The modifier to be applied to the text field
 * @param enabled Controls the enabled state of the text field
 * @param readOnly Controls the editable state of the text field
 * @param textStyle The style to be applied to the input text
 * @param placeholder The optional placeholder to be displayed when the text field is empty
 * @param leadingIcon The optional leading icon to be displayed at the start of the text field
 * @param trailingIcon The optional trailing icon to be displayed at the end of the text field
 * @param isError Indicates if the text field's current value is in error state
 * @param visualTransformation Transforms the visual representation of the input
 * @param keyboardOptions Software keyboard options that contain configuration such as KeyboardType and ImeAction
 * @param keyboardActions When the input service emits an IME action, the corresponding callback is called
 * @param singleLine When set to true, this text field becomes a single horizontally scrolling text field
 * @param maxLines The maximum height in terms of maximum number of visible lines
 * @param shape The shape of the text field's container
 * @param borderColor The color of the border around the text field
 * @param backgroundColor The background color of the text field
 */
@Composable
fun DesktopTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    shape: Shape = RoundedCornerShape(8.dp),
    borderColor: Color = MaterialTheme.colorScheme.outline,
    backgroundColor: Color = Color.Transparent
) {
    val interactionSource = remember { MutableInteractionSource() }
    val borderWidth = 1.dp

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(36.dp)
            .defaultMinSize(minHeight = 36.dp)
            .clip(shape)
            .border(borderWidth, borderColor, shape)
            .background(backgroundColor, shape),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    Box(Modifier.padding(end = 8.dp).align(Alignment.CenterVertically)) {
                        leadingIcon()
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 4.dp)
                        .align(Alignment.Top)
                ) {
                    if (value.isEmpty() && placeholder != null) {
                        placeholder()
                    }
                    innerTextField()
                }

                if (trailingIcon != null) {
                    Box(Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)) {
                        trailingIcon()
                    }
                }
            }
        }
    )
}

/**
 * A filled variant of the desktop TextField component.
 * This component has a fixed height of 36.dp to fit within desktop top bars,
 * with proper internal layout to ensure text is fully visible.
 */
@Composable
fun DesktopFilledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    shape: Shape = RoundedCornerShape(8.dp)
) {
    val interactionSource = remember { MutableInteractionSource() }
    val backgroundColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(36.dp)
            .defaultMinSize(minHeight = 36.dp)
            .clip(shape)
            .background(backgroundColor, shape),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle.copy(
            color = if (isError) MaterialTheme.colorScheme.error else LocalContentColor.current
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
            ) {
                if (leadingIcon != null) {
                    Box(Modifier.padding(end = 8.dp).align(Alignment.CenterVertically)) {
                        leadingIcon()
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 4.dp)
                        .align(Alignment.Top)
                ) {
                    if (value.isEmpty() && placeholder != null) {
                        placeholder()
                    }
                    innerTextField()
                }

                if (trailingIcon != null) {
                    Box(Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)) {
                        trailingIcon()
                    }
                }
            }
        }
    )
}
