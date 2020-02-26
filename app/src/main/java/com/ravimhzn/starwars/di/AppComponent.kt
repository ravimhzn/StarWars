package com.ravimhzn.stask.di

import android.app.Application
import com.ravimhzn.starwars.di.AppModule
import com.ravimhzn.starwars.di.BaseApplication
import com.ravimhzn.starwars.di.ActivityBuildersModule
import com.ravimhzn.starwars.di.ViewModelFactoryModule
import com.ravimhzn.starwars.utils.DataManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent {

    fun dataManager() : DataManager

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: BaseApplication)
}

