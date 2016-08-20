package com.imallan.gankmvp.model.post

import com.imallan.gankmvp.api.GankService
import com.imallan.playground.Post
import rx.Observable
import rx.schedulers.Schedulers

class PostRemoteSource(val gankService: GankService) {

    fun requestPosts(type: String, page: Int, limit: Int): Observable<List<Post>> {
        return gankService.getPost(type, limit, page)
                .map { it.results }
                .subscribeOn(Schedulers.io())
    }

}