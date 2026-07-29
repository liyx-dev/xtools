package com.liyx.xtools.ui.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.liyx.xtools.viewmodel.AudioLibraryViewModel
import com.liyx.xtools.ui.library.components.AudioRecordingCard


@Composable
fun AudioLibraryScreen(

    viewModel: AudioLibraryViewModel

) {

    val recordings by viewModel.library.collectAsState()

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {

        items(recordings) { recording ->

    AudioRecordingCard(

        recording = recording

    )

} 


    }

}
