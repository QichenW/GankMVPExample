package com.imallan.gankmvp.presenter.post

import com.imallan.gankmvp.extensions.loge
import com.imallan.gankmvp.extensions.pending
import com.imallan.gankmvp.model.post.PostRepository
import com.imallan.playground.Post
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription

class PostPresenter(private val postRepository: PostRepository) {

    private var view: PostView? = null
    private var mData: List<Post>? = null
    private val mPendingSubscriptions = CompositeSubscription()

    fun bind(view: PostView) {
        this.view = view
        if (mData != null) {
            view.showContent(mData!!)
        }
    }

    fun unbind(isFinishing: Boolean) {
        this.view = null
        if (isFinishing) mPendingSubscriptions.clear()
    }


    fun loadPosts() {
        postRepository.getAllPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.mData = it
                    view?.apply {
                        showContent(it)
                    }
                }, {
                    loge("Error: ${it.message}", it)
                    view?.showError()
                })
                .pending(mPendingSubscriptions)
    }

    fun requestPosts(type: String = "福利", page: Int = 1, limit: Int = 20) {
        view?.showLoading(true)
        postRepository.requestPosts(type, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showLoading(false)
                }, {
                    loge("Error: ${it.message}", it)
                    view?.showLoading(false)
                    view?.showError()
                })
                .pending(mPendingSubscriptions)
    }

}

interface PostView {

    fun showLoading(loading: Boolean = true)

    fun showError()

    fun showContent(posts: List<Post>)

}
