package com.imallan.gankmvp.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.imallan.gankmvp.api.GankService
import com.imallan.gankmvp.di.module.ApiModule
import com.imallan.gankmvp.di.module.ApplicationModule
import com.imallan.gankmvp.di.module.PostsModule
import com.imallan.gankmvp.di.module.RxModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ApiModule::class, RxModule::class))
interface ApplicationComponent {

    fun getApplication(): Application

    fun getContext(): Context

    fun getSharedPreference(): SharedPreferences

    fun getApiService(): GankService

//    fun plus(postsModule: PostsModule): MainActivityComponent

    fun plus(postsModule: PostsModule): PostsFragmentComponent
}
