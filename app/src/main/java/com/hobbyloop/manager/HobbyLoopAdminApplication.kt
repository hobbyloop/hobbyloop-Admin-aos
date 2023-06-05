package com.hobbyloop.manager

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 *   @Author Wonseok Oh
 *   @Date 2023/05/08
 *   @Company SKB
 *   @Email ows3090@sk.com
 */
@HiltAndroidApp
class HobbyLoopAdminApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
