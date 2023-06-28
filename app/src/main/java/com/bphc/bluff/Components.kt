package com.bphc.bluff

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MainMenuOption(painter: Painter, title: String, modifier: Modifier, onClick: () -> Unit) {
    OutlinedButton(onClick = onClick,
        modifier = modifier
            .padding(12.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, MaterialTheme.colors.onSurface),
//        colors = ButtonDefaults.outlinedButtonColors(
//            backgroundColor = MaterialTheme.colors.background,
//            contentColor = MaterialTheme.colors.onSurface
//        ),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.weight(7f), contentAlignment = Alignment.Center) {
                Icon(painter,
                    modifier = Modifier.size(48.dp),
                    contentDescription = null)
            }
            Text(modifier = Modifier.weight(3f), text = title)
        }

    }
}

@Preview
@Composable
fun MainMenuOptionPreview() {
    MainMenuOption(painter = rememberVectorPainter(image = Icons.Filled.Add), title = "Title", modifier = Modifier) {
        //pass
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun DropdownComponent(options: List<String>, label: String, padding: Dp, select: Int, onSelect: (String) -> Unit) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selection by rememberSaveable { mutableStateOf(select) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = Modifier.padding(top = padding, bottom = padding)
    ) {
        TextField(
            modifier = Modifier.border(
                width = 0.5.dp,
                color = MaterialTheme.colors.onSurface,
                shape = RoundedCornerShape(4.dp)),
            readOnly = true,
            value = options[selection],
            onValueChange = { },
            label = { Text(label) },
            trailingIcon = {
                CustomTrailIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selection = options.indexOf(selectionOption)
                        expanded = false
                        onSelect(selectionOption)
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CustomTrailIcon(
    expanded: Boolean,
    onIconClick: () -> Unit = {}
) {
    // Clear semantics here as otherwise icon will be a11y focusable but without an
    // action. When there's an API to check if Talkback is on, developer will be able to
    // expand the menu on icon click in a11y mode only esp. if using their own custom
    // trailing icon.
    IconButton(onClick = onIconClick, modifier = Modifier.clearAndSetSemantics { }) {
        Icon(
            Icons.Outlined.KeyboardArrowDown,
            "Trailing icon for exposed dropdown menu",
            Modifier.rotate(
                if (expanded)
                    180f
                else
                    360f
            )
        )
    }
}
