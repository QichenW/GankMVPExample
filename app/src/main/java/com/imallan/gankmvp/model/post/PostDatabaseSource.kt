package com.imallan.gankmvp.model.post

import com.imallan.gankmvp.extensions.inTransaction
import com.imallan.playground.Post
import io.realm.Realm
import io.realm.Sort
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers

class PostDatabaseSource(val realmScheduler: Scheduler) {

    fun getAllPosts(): Observable<List<Post>> {
        var realm: Realm? = null
        return Observable.fromCallable {
            realm = Realm.getDefaultInstance()
            realm!!
        }
                .subscribeOn(realmScheduler)
                .flatMap {
                    it.where(Post::class.java)
                            .findAllSortedAsync("publishedAt", Sort.DESCENDING)
                            .asObservable()
                }
                .filter { it.isLoaded }
                .map { realm!!.copyFromRealm(it) }
                .doOnUnsubscribe {
                    realm?.close()
                }
                .unsubscribeOn(realmScheduler)
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun save(posts: List<Post>) {
        return Realm.getDefaultInstance().inTransaction({
            copyToRealmOrUpdate(posts)
        }, closeAfterTransaction = true)
    }

}