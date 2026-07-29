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

    value: String,

    label: String,

    modifier: Modifier = Modifier

) {

    Card(

        modifier = modifier,

        shape = MaterialTheme.shapes.large,

        elevation = CardDefaults.cardElevation(

            defaultElevation = 4.dp

        )

    ) {

        Column(

            modifier = Modifier.padding(20.dp)

        ) {

            Text(

                text = value,

                style = MaterialTheme.typography.headlineSmall,

                fontWeight = FontWeight.Bold,

                color = MaterialTheme.colorScheme.primary

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(

                text = label,

                style = MaterialTheme.typography.bodyMedium

            )

        }

    }

}
