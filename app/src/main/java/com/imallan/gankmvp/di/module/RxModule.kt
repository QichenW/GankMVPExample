package com.imallan.gankmvp.di.module

import android.os.Looper
import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Named

@Module
class RxModule {

    @Provides
    @Named("main")
    fun providesMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named("io")
    fun providesIOScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Named("computation")
    fun providesComputationScheduler(): Scheduler = Schedulers.computation()

    @Provides
    @Named("realm")
    fun providesRealmScheduler(): Scheduler {
        Executors.newSingleThreadExecutor().run {
            val myLooper: Looper = submit<Looper> {
                Looper.prepare()
                return@submit Looper.myLooper()
            }.get()
            execute {
                Looper.loop()
            }
            return AndroidSchedulers.from(myLooper)
        }
    }

}
