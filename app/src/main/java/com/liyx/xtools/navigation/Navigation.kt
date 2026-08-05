package com.liyx.xtools.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.liyx.xtools.AppContainer
import com.liyx.xtools.ui.HomeScreen
import com.liyx.xtools.ui.VoiceStudioScreen

import androidx.lifecycle.viewmodel.compose.viewModel
import com.liyx.xtools.ui.library.AudioLibraryScreen
import com.liyx.xtools.viewmodel.AudioLibraryViewModel
import com.liyx.xtools.viewmodel.AudioLibraryViewModelFactory
import com.liyx.xtools.ui.library.VoiceLibraryScreen
import com.liyx.xtools.viewmodel.VoiceViewModel
import com.liyx.xtools.viewmodel.VoiceViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun XtoolsNavigation(

    appContainer: AppContainer

) {

    val navController = rememberNavController()
val voiceViewModel: VoiceViewModel = viewModel(
    factory = VoiceViewModelFactory(appContainer)
)


    NavHost(

        navController = navController,

        startDestination = Screen.Home.route

    ) {

        composable(Screen.Home.route) {

            HomeScreen(

                onOpenVoiceStudio = {

                    navController.navigate(

                        Screen.VoiceStudio.route

                    )

                }

            )

        }

        composable(Screen.VoiceStudio.route) {

            VoiceStudioScreen(

    viewModel = voiceViewModel,
    onBack = {

        navController.popBackStack()

    },

    onOpenLibrary = {

        navController.navigate(

            Screen.AudioLibrary.route

        )

    },
onOpenVoiceLibrary = {

        navController.navigate(

            Screen.VoiceLibrary.route

        )

    }


)

        }

composable(Screen.AudioLibrary.route) {

    val libraryViewModel: AudioLibraryViewModel = viewModel(

        factory = AudioLibraryViewModelFactory(appContainer)

    )

    AudioLibraryScreen(

        viewModel = libraryViewModel

    )

}

composable(Screen.VoiceLibrary.route) {

    VoiceLibraryScreen(

    viewModel = voiceViewModel,
        onBack = {

            navController.popBackStack()

        }

    )

}



    }

}
