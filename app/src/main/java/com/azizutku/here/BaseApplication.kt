package com.azizutku.here

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.*
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.Default).launch {
            val builder = VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())
            if (BuildConfig.DEBUG) {
                Timber.plant(DebugTree())
            }
        }
    }

}
