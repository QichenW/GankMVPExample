package com.imallan.gankmvp.model.post

import com.imallan.playground.Post
import rx.Observable

class PostRepository(private val remoteSource: PostRemoteSource,
                     private val databaseSource: PostDatabaseSource,
                     private val memorySource: PostMemorySource) {

    fun getAllPosts(): Observable<List<Post>> {
        return Observable.concat(
                memorySource.getAllPosts(),
                databaseSource.getAllPosts()
                        .doOnNext { memorySource.cache(it) }
        )
    }

    fun requestPosts(type: String, page: Int, limit: Int): Observable<List<Post>> {
        return remoteSource.requestPosts(type, page, limit)
                .doOnNext { databaseSource.save(it) }
                .doOnNext { memorySource.cache(it) }
    }

}
