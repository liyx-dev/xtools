package com.liyx.xtools.design.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    subtitle: String = "",
    showBack: Boolean = false,
    onBack: (() -> Unit)? = null
) {

    CenterAlignedTopAppBar(

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),

        title = {

            Column {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )

                if (subtitle.isNotBlank()) {

                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                }

            }

        },

        navigationIcon = {

            if (showBack && onBack != null) {

                IconButton(
                    onClick = onBack
                ) {

                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )

                }

            }

        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)

    )

}
