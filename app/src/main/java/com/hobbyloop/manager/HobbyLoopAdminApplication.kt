package com.hobbyloop.manager

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
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
        KakaoSdk.init(this, BuildConfig.KAKAO_APP_KEY)
    }
}
