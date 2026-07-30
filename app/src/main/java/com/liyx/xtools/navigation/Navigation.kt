package com.liyx.xtools.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.liyx.xtools.AppContainer
import com.liyx.xtools.ui.HomeScreen
import com.liyx.xtools.ui.VoiceStudioScreen

@Composable
fun XtoolsNavigation(

    appContainer: AppContainer

) {

    val navController = rememberNavController()

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

                appContainer = appContainer,

                onBack = {

                    navController.popBackStack()

                }

            )

        }

    }

}
