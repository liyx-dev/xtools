package com.liyx.xtools.ui.export

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.liyx.xtools.core.export.ExportAudio
import com.liyx.xtools.viewmodel.ExportViewModel

@Composable
fun ExportScreen(

    exportAudio: ExportAudio,

    viewModel: ExportViewModel

) {

    val success by viewModel
        .exportSuccess
        .collectAsState()

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text(

            text = exportAudio.title,

            style = MaterialTheme.typography.titleLarge

        )

        Button(

            onClick = {

                viewModel.export(exportAudio)

            }

        ) {

            Text("Export Audio")

        }

        if (success) {

            Text(

                text = "Export completed successfully.",

                style = MaterialTheme.typography.bodyMedium

            )

        }

    }

}
