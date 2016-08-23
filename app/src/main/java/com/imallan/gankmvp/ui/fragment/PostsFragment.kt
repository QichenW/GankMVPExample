package com.imallan.gankmvp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imallan.gankmvp.R
import com.imallan.gankmvp.presenter.post.PostPresenter
import com.imallan.gankmvp.presenter.post.PostView
import com.imallan.gankmvp.ui.adapter.PostsAdapter
import com.imallan.playground.Post

class PostsFragment : Fragment(), PostView {

    private lateinit var mErrorView: View
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mProgressView: View
    private val mAdapter = PostsAdapter()
    lateinit var presenter: PostPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.run {
            mErrorView = findViewById(R.id.error_view)
            mRecyclerView = findViewById(R.id.recycler_posts) as RecyclerView
            mProgressView = findViewById(R.id.progress_loading)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mRecyclerView.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(
                    context,
                    resources.getInteger(R.integer.postsSpanCount)
            )
            setHasFixedSize(true)
        }

        if (activity is PostsAdapter.PostClickListener) {
            mAdapter.listener = activity as PostsAdapter.PostClickListener
        }

        presenter.bind(this)

        presenter.loadPosts()

        if (savedInstanceState == null) presenter.requestPosts()

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

    override fun onDestroyView() {
        presenter.unbind(false)
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.unbind(true)
        super.onDestroy()
    }

}
