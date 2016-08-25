package com.imallan.gankmvp.ui.activity

import android.os.Bundle
import com.imallan.gankmvp.R
import com.imallan.gankmvp.ui.adapter.PostsAdapter
import com.imallan.gankmvp.ui.fragment.ImageFragment
import com.imallan.gankmvp.ui.fragment.PostsFragment
import com.imallan.playground.Post

class MainActivity : BaseActivity(), PostsAdapter.PostClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fragment)

        if (savedInstanceState == null) {
            val postsFragment = PostsFragment()
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
