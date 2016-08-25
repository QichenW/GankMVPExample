package com.imallan.gankmvp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.animation.Animation

abstract class BaseFragment : Fragment() {

    companion object {
        const val STATE_PRESENTER_KEY = "STATE_PRESENTER_KEY"
    }

    private var mAvoidAnimation = false

    protected lateinit var componentKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        componentKey = if (savedInstanceState != null) {
            savedInstanceState.getString(STATE_PRESENTER_KEY) ?: createUniqueComponentKey()
        } else {
            createUniqueComponentKey()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_PRESENTER_KEY, componentKey)
    }

    private fun createUniqueComponentKey(): String {
        return "${javaClass.simpleName}${System.currentTimeMillis()}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mAvoidAnimation = activity?.isChangingConfigurations ?: false
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        val avoidAnimation = mAvoidAnimation
        mAvoidAnimation = false
        if (avoidAnimation) {
            return object : Animation() {}
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }
}