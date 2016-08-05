package com.imallan.gankmvp.model.post

import com.imallan.gankmvp.extensions.inTransaction
import com.imallan.playground.Post
import io.realm.Realm
import rx.Observable
import rx.android.schedulers.AndroidSchedulers

class PostDatabaseSource {

    fun getAllPosts(): Observable<List<Post>> {
        return Realm.getDefaultInstance().run {
            where(Post::class.java).findAllAsync()
                    .asObservable()
                    .filter { it.isLoaded }
                    .map { copyFromRealm(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .onBackpressureDrop()
                    .doOnUnsubscribe { close() }
        }
    }

    fun save(posts: List<Post>) {
        return Realm.getDefaultInstance().inTransaction({
            copyToRealmOrUpdate(posts)
        }, closeAfterTransaction = true)
    }

}