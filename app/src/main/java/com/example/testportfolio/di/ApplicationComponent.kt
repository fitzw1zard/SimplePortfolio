package com.example.testportfolio.di

import android.app.Application
import com.example.testportfolio.MainActivity
import com.example.testportfolio.MainApp
import com.example.testportfolio.presentation.ui.CoinDetailFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: CoinDetailFragment)
    fun inject(application: MainApp)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}