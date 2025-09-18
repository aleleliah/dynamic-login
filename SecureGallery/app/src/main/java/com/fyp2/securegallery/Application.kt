package com.fyp2.securegallery

import android.app.Application
import com.fyp2.securegallery.util.Stash

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        Stash.init(this)
    }
}