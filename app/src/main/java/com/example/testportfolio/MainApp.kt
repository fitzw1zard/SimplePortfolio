package com.example.testportfolio

import android.app.Application
import com.example.testportfolio.di.DaggerApplicationComponent

class MainApp: Application() {

        val component by lazy {
            DaggerApplicationComponent.factory().create(this)
        }
}