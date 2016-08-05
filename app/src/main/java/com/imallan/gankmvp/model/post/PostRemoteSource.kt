package com.imallan.gankmvp.model.post

import com.imallan.gankmvp.api.API
import com.imallan.playground.Post
import rx.Observable
import rx.schedulers.Schedulers

class PostRemoteSource {

    fun requestPosts(type: String, page: Int, limit: Int): Observable<List<Post>> {
        return API.instance.getPost(type, limit, page)
                .map { it.results }
                .subscribeOn(Schedulers.io())
    }

}