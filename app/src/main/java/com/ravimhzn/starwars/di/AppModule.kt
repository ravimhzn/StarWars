package com.ravimhzn.starwars.di

import com.ravimhzn.starwars.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun testString(): String {
        return "DI Working Correctly"
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        //https://swapi.co/ sometimes takes alot of time to load, so increasing timeout seconds.
//        val okHttpClient = OkHttpClient.Builder()
//            .connectTimeout(20, TimeUnit.SECONDS)
//            .writeTimeout(20, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .build()
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}