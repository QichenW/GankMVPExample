package com.imallan.gankmvp.di.component

import com.imallan.gankmvp.di.module.PostsModule
import com.imallan.gankmvp.di.scope.PostsScope
import com.imallan.gankmvp.ui.fragment.PostsFragment
import dagger.Subcomponent

@PostsScope
@Subcomponent(modules = arrayOf(PostsModule::class))
interface PostsFragmentComponent {

    fun inject(postsFragment: PostsFragment)
}
