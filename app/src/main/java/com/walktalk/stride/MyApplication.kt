package com.walktalk.stride

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.walktalk.stride.data.api.RetrofitInstance

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        RetrofitInstance.init(this)
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)
    }
}
