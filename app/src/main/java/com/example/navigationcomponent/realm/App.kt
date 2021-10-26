package com.example.navigationcomponent.realm

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
    }