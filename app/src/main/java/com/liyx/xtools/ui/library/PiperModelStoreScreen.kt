package com.liyx.xtools.ui.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.liyx.xtools.AppContainer
import com.liyx.xtools.core.providers.PiperModel
import com.liyx.xtools.design.components.TopBar
import com.liyx.xtools.viewmodel.PiperModelStoreViewModel
import com.liyx.xtools.viewmodel.PiperModelStoreViewModelFactory

@Composable
fun PiperModelStoreScreen(

    appContainer: AppContainer,

    onBack: () -> Unit

) {

    val viewModel: PiperModelStoreViewModel =
        viewModel(
            factory =
                PiperModelStoreViewModelFactory(
                    appContainer
                )
        )

    val state by
        viewModel.uiState.collectAsState()

    Scaffold(

        topBar = {

            TopBar(

                title = "Piper Model Store",

                subtitle = "Install offline AI voices",

                showBack = true,

                onBack = onBack

            )

        }

    ) { padding ->

        LazyColumn(

            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(padding),

            contentPadding =
                PaddingValues(20.dp),

            verticalArrangement =
                Arrangement.spacedBy(16.dp)

        ) {

            item {

                Text(

                    text = "🎙 Piper AI Voices",

                    style =
                        MaterialTheme
                            .typography
                            .headlineSmall

                )

            }

            item {

                Text(

                    text =
                        "Download a voice once and use it offline.",

                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium,

                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant

                )

            }

            if (state.message.isNotBlank()) {

                item {

                    Text(

                        text = state.message,

                        color =
                            MaterialTheme
                                .colorScheme
                                .primary

                    )

                }

            }

            if (state.error != null) {

                item {

                    Text(

                        text = state.error!!,

                        color =
                            MaterialTheme
                                .colorScheme
                                .error

                    )

                }

            }

            items(

                items = state.models,

                key = { it.id }

            ) { model ->

                PiperModelCard(

                    model = model,

                    downloading =
                        state.downloadingId ==
                            model.id,

                    onDownload = {

                        viewModel.download(
                            model.id
                        )

                    }

                )

            }

        }

    }

}

@Composable
private fun PiperModelCard(

    model: PiperModel,

    downloading: Boolean,

    onDownload: () -> Unit

) {

    Card(

        modifier =
            Modifier.fillMaxWidth()

    ) {

        Column(

            modifier =
                Modifier.padding(18.dp),

            verticalArrangement =
                Arrangement.spacedBy(8.dp)

        ) {

            Text(

                text = model.name,

                style =
                    MaterialTheme
                        .typography
                        .titleMedium

            )

            Text(

                text = model.language,

                style =
                    MaterialTheme
                        .typography
                        .bodyMedium,

                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant

            )

            Text(

                text =
                    if (model.downloaded)
                        "✓ Installed"
                    else
                        "Available for download",

                style =
                    MaterialTheme
                        .typography
                        .bodySmall,

                color =
                    if (model.downloaded)
                        MaterialTheme
                            .colorScheme
                            .primary
                    else
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant

            )

            Button(

                onClick = onDownload,

                enabled =
                    !model.downloaded &&
                    !downloading,

                modifier =
                    Modifier.fillMaxWidth()

            ) {

                if (downloading) {

                    CircularProgressIndicator(
                        modifier = Modifier.padding(2.dp)
                    )

                } else {

                    Text(

                        text =
                            if (model.downloaded)
                                "Installed"
                            else
                                "Download Voice"

                    )

                }

            }

        }

    }

}
