package com.liyx.xtools.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScriptEditorCard(

    text: String,

    characterCount: Int,

    estimatedDuration: Long,

    onTextChanged: (String) -> Unit

) {

    Column {

        OutlinedTextField(

            value = text,

            onValueChange = onTextChanged,

            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),

            label = {

                Text("Your Script")

            },

            placeholder = {

                Text("Paste or type your script here...")

            },

            shape = MaterialTheme.shapes.large

        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                "$characterCount characters",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                "${estimatedDuration / 1000}s",
                style = MaterialTheme.typography.bodySmall
            )

        }

    }

}
