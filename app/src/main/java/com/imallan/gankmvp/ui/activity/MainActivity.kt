package com.imallan.gankmvp.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.imallan.gankmvp.GankMVPApplication
import com.imallan.gankmvp.R
import com.imallan.gankmvp.di.module.MainActivityModule
import com.imallan.gankmvp.presenter.post.PostPresenter
import com.imallan.gankmvp.presenter.post.PostView
import com.imallan.gankmvp.ui.adapter.PostsAdapter
import com.imallan.playground.Post
import com.imallan.toothpick.bind
import javax.inject.Inject

class MainActivity : BaseActivity(), PostView {

    val mErrorView: View by bind(R.id.error_view)
    val mRecyclerView: RecyclerView by bind(R.id.recycler_posts)
    val mProgressView: View by bind(R.id.progress_loading)
    private val mAdapter = PostsAdapter()

    @Inject
    lateinit var mPresenter: PostPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(
                    this@MainActivity,
                    resources.getInteger(R.integer.postsSpanCount)
            )
            setHasFixedSize(true)
        }

        GankMVPApplication.getComponent(componentKey, {
            plus(MainActivityModule())
        }).inject(this)

        mPresenter.bind(this)

        mPresenter.loadPosts()

        if (savedInstanceState == null) mPresenter.requestPosts()

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unbind(isFinishing)
        if (isFinishing) {
            GankMVPApplication.removeComponent(componentKey)
        }
    }

    override fun showLoading(loading: Boolean) {
        mProgressView.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun showError() {
        mErrorView.visibility = View.VISIBLE
        mRecyclerView.visibility = View.GONE
    }

    override fun showContent(posts: List<Post>) {
        mAdapter.setData(posts)
        mErrorView.visibility = View.GONE
        mRecyclerView.visibility = View.VISIBLE
    }

}
