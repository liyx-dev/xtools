package com.liyx.xtools.design.components

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import com.liyx.xtools.design.XtoolsColors

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

        modifier = modifier.fillMaxWidth(),

        label = {

            if (label.isNotEmpty()) {

                Text(
                    text = label,
                    color = XtoolsColors.TextSecondary
                )

            }

        },

        placeholder = {

            if (placeholder.isNotEmpty()) {

                Text(
                    text = placeholder,
                    color = XtoolsColors.TextMuted
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

                        contentDescription = "Clear",

                        tint = XtoolsColors.IconSecondary

                    )

                }

            }

        },

        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = XtoolsColors.TextPrimary
        ),

        colors = OutlinedTextFieldDefaults.colors(

            focusedContainerColor = XtoolsColors.Surface2,

            unfocusedContainerColor = XtoolsColors.Surface2,

            disabledContainerColor = XtoolsColors.Surface,

            focusedBorderColor = XtoolsColors.BrandGreen,

            unfocusedBorderColor = XtoolsColors.CardBorder,

            disabledBorderColor = XtoolsColors.Divider,

            cursorColor = XtoolsColors.BrandGreen,

            focusedLabelColor = XtoolsColors.BrandGreen,

            unfocusedLabelColor = XtoolsColors.TextSecondary,

            focusedTextColor = XtoolsColors.TextPrimary,

            unfocusedTextColor = XtoolsColors.TextPrimary,

            disabledTextColor = XtoolsColors.TextDisabled,

            focusedPlaceholderColor = XtoolsColors.TextMuted,

            unfocusedPlaceholderColor = XtoolsColors.TextMuted

        )

    )

}
