package com.liyx.xtools.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProjectTitleCard(
    title: String,
    onTitleChanged: (String) -> Unit
) {

    OutlinedTextField(

        value = title,

        onValueChange = onTitleChanged,

        modifier = Modifier.fillMaxWidth(),

        singleLine = true,

        label = {

            Text("Project Title")

        },

        placeholder = {

            Text("Morning Motivation")

        },

        shape = MaterialTheme.shapes.large

    )

}
