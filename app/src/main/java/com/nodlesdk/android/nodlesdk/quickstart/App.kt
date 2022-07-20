package com.nodlesdk.android.nodlesdk.quickstart

import android.app.Application
import io.nodle.sdk.android.Nodle

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        // init Nodle
        Nodle.init(this)
    }
}