package com.imallan.gankmvp.model.post

import com.imallan.playground.Post
import rx.Observable

class PostMemorySource {

    var posts: List<Post>? = null

    fun getAllPosts(): Observable<List<Post>> {
        return Observable.defer {
            if (posts == null) Observable.empty() else Observable.just(posts!!)
        }
    }

    fun cache(posts: List<Post>) {
        this.posts = posts
    }

}
