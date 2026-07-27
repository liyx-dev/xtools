package com.liyx.xtools

import android.app.Application

/**
 * Xtools Application
 *
 * The single entry point of the application.
 *
 * Responsible for creating and holding
 * shared services used throughout the app.
 */
class XtoolsApp : Application() {

    /**
     * Global dependency container.
     *
     * Accessible anywhere in the app through:
     *
     * val app = context.applicationContext as XtoolsApp
     * app.container
     */
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        // Create all shared app services.
        container = AppContainer(this)
    }
}
