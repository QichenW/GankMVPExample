package com.imallan.gankmvp.di.module

import android.os.Looper
import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
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
        var looper: Looper? = null
        var block = true
        val thread: Thread = Thread({
            Looper.prepare()
            looper = Looper.myLooper()
            block = false
            Looper.loop()
        })
        thread.start()
        while (block) {
            //
        }
        return AndroidSchedulers.from(looper!!)
    }

}
