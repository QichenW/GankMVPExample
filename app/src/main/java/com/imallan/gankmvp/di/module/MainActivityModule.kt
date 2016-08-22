package com.imallan.gankmvp.di.module

import com.imallan.gankmvp.api.GankService
import com.imallan.gankmvp.di.scope.MainActivityScope
import com.imallan.gankmvp.model.post.PostDatabaseSource
import com.imallan.gankmvp.model.post.PostMemorySource
import com.imallan.gankmvp.model.post.PostRemoteSource
import com.imallan.gankmvp.model.post.PostRepository
import com.imallan.gankmvp.presenter.post.PostPresenter
import dagger.Module
import dagger.Provides
import rx.Scheduler
import javax.inject.Named

@Module
class MainActivityModule {

    @Provides
    @MainActivityScope
    fun providesPostPresenter(@Named("main") scheduler: Scheduler,
                              postRepository: PostRepository): PostPresenter {
        return PostPresenter(scheduler, postRepository)
    }

    @Provides
    @MainActivityScope
    fun providesPostRepository(remoteSource: PostRemoteSource,
                               databaseSource: PostDatabaseSource,
                               memorySource: PostMemorySource): PostRepository {
        return PostRepository(remoteSource, databaseSource, memorySource)
    }

    @Provides
    @MainActivityScope
    fun providesPostRemoteSource(gankService: GankService): PostRemoteSource {
        return PostRemoteSource(gankService)
    }

    @Provides
    @MainActivityScope
    fun providesPosDatabaseSource(@Named("realm") scheduler: Scheduler): PostDatabaseSource {
        return PostDatabaseSource(scheduler)
    }

    @Provides
    @MainActivityScope
    fun providesPostMemorySource(): PostMemorySource {
        return PostMemorySource()
    }

}
