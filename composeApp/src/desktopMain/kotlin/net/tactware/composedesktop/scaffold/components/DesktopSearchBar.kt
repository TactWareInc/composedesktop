package net.tactware.composedesktop.scaffold.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup

/**
 * A custom search bar component designed specifically for desktop applications.
 * This component has an oval shape with completely rounded corners and a slot-based design
 * that allows for customization of the results section.
 *
 * @param query The current search query text
 * @param onQueryChange Callback invoked when the search query changes
 * @param onSearch Callback invoked when the search is submitted
 * @param active Whether the search bar is currently active/expanded
 * @param onActiveChange Callback invoked when the active state changes
 * @param modifier The modifier to be applied to the search bar
 * @param placeholder The optional placeholder to be displayed when the search field is empty
 * @param leadingIcon The optional leading icon to be displayed at the start of the search bar
 * @param trailingIcon The optional trailing icon to be displayed at the end of the search bar
 * @param shape The shape of the search bar (defaults to a pill shape)
 * @param resultsContent The slot for displaying search results or suggestions
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesktopSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)? = {
        Text(
            "Search",
            style = LocalTextStyle.current.copy(
                fontSize = 13.sp,
                color = LocalContentColor.current.copy(alpha = 0.6f)
            )
        )
    },
    leadingIcon: @Composable (() -> Unit)? = {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = LocalContentColor.current.copy(alpha = 0.7f)
        )
    },
    trailingIcon: @Composable (() -> Unit)? = {
        if (query.isNotEmpty()) {
            IconButton(
                onClick = { onQueryChange("") },
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear search",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    },
    shape: Shape = CircleShape,
    resultsContent: @Composable () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val borderColor = MaterialTheme.colorScheme.outline
    val backgroundColor = MaterialTheme.colorScheme.surface
    Column(modifier = modifier) {
        // Search bar
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .defaultMinSize(minHeight = 36.dp)
                .clip(shape)
                .border(1.dp, borderColor, shape)
                .background(backgroundColor, shape)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isFocused = it.isFocused
                    if (it.isFocused != active) {
                        onActiveChange(it.isFocused)
                    }
                },
            textStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch(query) }),
            singleLine = true,
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
                            .weight(1f).align(Alignment.Top).padding(top = 4.dp)
                    ) {
                        if (query.isEmpty() && placeholder != null) {
                            placeholder()
                        }
                        innerTextField()
                    }

                    if (trailingIcon != null) {
                        Box(Modifier.padding(start = 4.dp).align(Alignment.CenterVertically)) {
                            trailingIcon()
                        }
                    }

                    if (active) {
                        Popup(popupPositionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(), onDismissRequest =
                        { onActiveChange(false) }) {
                            Card {
                                resultsContent()
                            }
                        }
                    }
                }
            }
        )
    }
}

/**
 * A search result item component to be used within the DesktopSearchBar's resultsContent.
 *
 * @param text The text to display for this result item
 * @param onClick Callback invoked when this result item is clicked
 * @param modifier The modifier to be applied to the result item
 * @param leadingContent Optional leading content (e.g., an icon)
 * @param trailingContent Optional trailing content
 */
@Composable
fun SearchResultItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingContent != null) {
                Box(modifier = Modifier.padding(end = 16.dp)) {
                    leadingContent()
                }
            }

            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )

            if (trailingContent != null) {
                Box(modifier = Modifier.padding(start = 16.dp)) {
                    trailingContent()
                }
            }
        }
    }
}

/**
 * A section header component to be used within the DesktopSearchBar's resultsContent
 * to organize search results into categories.
 *
 * @param title The title of the section
 * @param modifier The modifier to be applied to the section header
 */
@Composable
fun SearchResultSectionHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelLarge.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}
