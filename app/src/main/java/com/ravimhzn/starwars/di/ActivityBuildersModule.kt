package com.ravimhzn.starwars.di


import com.ravimhzn.starwars.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [

        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}
