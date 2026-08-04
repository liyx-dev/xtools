package com.liyx.xtools.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun QuickStatCard(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    icon: String = ""
)

 {

    Card(

        modifier = modifier,

        shape = MaterialTheme.shapes.large,

        elevation = CardDefaults.cardElevation(

            defaultElevation = 4.dp

        )

    ) {

        Column(

    modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)

)

 {


if (icon.isNotEmpty()) {

Text(

    text = icon,

    style = MaterialTheme.typography.headlineSmall,

    color = MaterialTheme.colorScheme.primary

)

    Spacer(
        modifier = Modifier.height(8.dp)
    )

}
            Text(

                text = value,

                style = MaterialTheme.typography.headlineSmall,

                fontWeight = FontWeight.Bold,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(modifier = Modifier.height(8.dp))

       Text(

    text = label,

    style = MaterialTheme.typography.bodyMedium,

    color = MaterialTheme.colorScheme.onSurfaceVariant

)

        }

    }

}
