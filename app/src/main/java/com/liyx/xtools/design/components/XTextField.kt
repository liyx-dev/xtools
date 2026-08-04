package com.liyx.xtools.design.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun XTextField(

    value: String,

    onValueChange: (String) -> Unit,

    modifier: Modifier = Modifier,

    label: String = "",

    placeholder: String = "",

    singleLine: Boolean = false,

    enabled: Boolean = true,

    readOnly: Boolean = false,

    trailingClearButton: Boolean = false

) {

    OutlinedTextField(

        value = value,

        onValueChange = onValueChange,

        modifier = modifier
            .fillMaxWidth(),

        label = {

            if (label.isNotEmpty()) {

                Text(label)

            }

        },

        placeholder = {

            if (placeholder.isNotEmpty()) {

                Text(
                    placeholder,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }

        },

        singleLine = singleLine,

        enabled = enabled,

        readOnly = readOnly,

        shape = RoundedCornerShape(22.dp),

        trailingIcon = {

            if (trailingClearButton && value.isNotEmpty()) {

                IconButton(

                    onClick = {

                        onValueChange("")

                    }

                ) {

                    Icon(

                        imageVector = Icons.Outlined.Clear,

                        contentDescription = "Clear"

                    )

                }

            }

        },

        colors = OutlinedTextFieldDefaults.colors(

            focusedContainerColor =
                MaterialTheme.colorScheme.surface,

            unfocusedContainerColor =
                MaterialTheme.colorScheme.surface,

            disabledContainerColor =
                MaterialTheme.colorScheme.surface,

            focusedBorderColor =
                MaterialTheme.colorScheme.primary,

            unfocusedBorderColor =
                MaterialTheme.colorScheme.outline,

            cursorColor =
                MaterialTheme.colorScheme.primary,

            focusedLabelColor =
                MaterialTheme.colorScheme.primary,

            unfocusedLabelColor =
                MaterialTheme.colorScheme.onSurfaceVariant,

            focusedTextColor =
                MaterialTheme.colorScheme.onSurface,

            unfocusedTextColor =
                MaterialTheme.colorScheme.onSurface

        )

    )

}
