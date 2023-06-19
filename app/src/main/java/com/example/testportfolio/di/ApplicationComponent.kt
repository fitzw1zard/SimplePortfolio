package com.example.testportfolio.di

import android.app.Activity
import android.app.Application
import com.example.testportfolio.MainActivity
import com.example.testportfolio.presentation.ui.CoinDetailFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}