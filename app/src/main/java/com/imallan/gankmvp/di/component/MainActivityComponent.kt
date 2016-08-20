package com.imallan.gankmvp.di.component

import com.imallan.gankmvp.di.module.MainActivityModule
import com.imallan.gankmvp.di.scope.MainActivityScope
import com.imallan.gankmvp.ui.activity.MainActivity
import dagger.Subcomponent

@MainActivityScope
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

}
