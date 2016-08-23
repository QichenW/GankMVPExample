package com.imallan.gankmvp.ui.activity

import android.os.Bundle
import com.imallan.gankmvp.GankMVPApplication
import com.imallan.gankmvp.R
import com.imallan.gankmvp.di.module.MainActivityModule
import com.imallan.gankmvp.presenter.post.PostPresenter
import com.imallan.gankmvp.ui.adapter.PostsAdapter
import com.imallan.gankmvp.ui.fragment.ImageFragment
import com.imallan.gankmvp.ui.fragment.PostsFragment
import com.imallan.playground.Post
import javax.inject.Inject

class MainFragmentActivity : BaseActivity(), PostsAdapter.PostClickListener {

    @Inject
    lateinit var mPresenter: PostPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fragment)

        GankMVPApplication.getComponent(componentKey, {
            plus(MainActivityModule())
        }).inject(this)

        if (savedInstanceState == null) {
            val postsFragment = PostsFragment()
            postsFragment.presenter = mPresenter
            supportFragmentManager.beginTransaction()
                    .add(R.id.activity_main_fragment, postsFragment)
                    .commit()
        }
    }

    override fun onPostClicked(post: Post) {
        val fragment = ImageFragment()
        fragment.imageUrl = post.url
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_up_in, R.anim.fade_scale_out, R.anim.fade_scale_in, R.anim.slide_down_out)
                .replace(R.id.activity_main_fragment, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }
}
