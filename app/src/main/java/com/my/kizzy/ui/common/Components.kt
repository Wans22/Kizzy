package com.my.kizzy.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BackButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = Icons.Outlined.ArrowBack.name
        )
    }
}

@Composable
fun PreferencesHint(
    title: String = "Title ".repeat(2),
    description: String? = "Description text ".repeat(3),
    icon: ImageVector?,
    onClick: () -> Unit = {},
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .size(24.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 0.dp, end = 12.dp)
        ) {
            with(MaterialTheme) {

                Text(
                    text = title,
                    maxLines = 1,
                    style = typography.titleLarge.copy(fontSize = 20.sp),
                    color = colorScheme.onSecondaryContainer
                )
                if (description != null)
                    Text(
                        text = description,
                        color = colorScheme.onSecondaryContainer,
                        maxLines = 2, overflow = TextOverflow.Ellipsis,
                        style = typography.bodyMedium,
                    )
            }
        }
    }
}

@Composable
fun PreferenceSingleChoiceItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = text,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onSurface, overflow = TextOverflow.Ellipsis
                )
            }
            RadioButton(
                selected = selected,
                onClick = onClick,
                modifier = Modifier.padding(start = 20.dp, end = 6.dp),
            )
        }
    }
}


@Composable
fun SwitchBar(
    title: String,
    isChecked: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(with(MaterialTheme.colorScheme) {
            if (isChecked) primaryContainer else outline })
            .toggleable(enabled){
                onClick()
            }
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
        ) {
        with(MaterialTheme) {
            Text(
                modifier = Modifier.weight(4f),
                text = title,
                maxLines = 1,
                style = typography.titleLarge.copy(fontSize = 20.sp),
                color = if (isChecked) colorScheme.onSurface else colorScheme.surface,
                overflow = TextOverflow.Ellipsis
            )
            KSwitch(
                modifier = Modifier.weight(1f),
                checked = isChecked,
                enable = enabled
            ){
                if (enabled) onClick()
            }
        }
    }
}

@Preview
@Composable
fun PreviewSwitchBar() {
    Box(Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center){
        var state by remember { mutableStateOf(false)}
        SwitchBar(title = "SwitchBar", isChecked = state) {
            state = !state
        }
    }
}

@Composable
fun PreferenceSwitch(
    title: String = "",
    description: String? = null,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    isChecked: Boolean = true,
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = if (enabled) Modifier.clickable { onClick() } else Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon?.let {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis
                )
                if (!description.isNullOrEmpty())
                    Text(
                        text = description,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 5,
                        style = MaterialTheme.typography.bodyMedium,
                    )
            }
            KSwitch(
                checked = isChecked,
                modifier = Modifier.padding(start = 20.dp, end = 6.dp),
                enable = enabled
            )

        }
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun SettingItem(
    title: String,
    description: String = "",
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 20.dp),
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onClick() }
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = title,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .size(25.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 0.dp)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 5,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun PreferenceSubtitle(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp, 5.dp),
    text: String,
    color: Color = MaterialTheme.colorScheme.primary,
    style: TextStyle = MaterialTheme.typography.labelLarge
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style
    )
}


@Composable
fun CreditItem(
    title: String,
    description: String? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = if (enabled) Modifier.clickable { onClick() } else Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                with(MaterialTheme) {
                    Text(
                        text = title,
                        maxLines = 1,
                        style = typography.titleMedium,
                        color = colorScheme.onSurface
                    )
                    Text(
                        text = description.toString(),
                        color = colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = typography.bodyMedium,
                    )
                }
            }
        }
    }

}
