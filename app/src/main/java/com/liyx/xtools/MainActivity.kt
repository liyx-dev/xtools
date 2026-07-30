package com.liyx.xtools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.liyx.xtools.design.XtoolsTheme
import com.liyx.xtools.navigation.XtoolsNavigation

class MainActivity : ComponentActivity() {

    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContainer = AppContainer(applicationContext)

        setContent {
            XtoolsTheme {
                XtoolsNavigation(
                    appContainer = appContainer
                )
            }
        }
    }
}
