package com.imallan.gankmvp.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    companion object {
        const val STATE_PRESENTER_KEY = "STATE_PRESENTER_KEY"
    }

    lateinit private var mUniquePresenterKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUniquePresenterKey = if (savedInstanceState != null) {
            savedInstanceState.getString(STATE_PRESENTER_KEY) ?: createUniquePresenterKey()
        } else {
            createUniquePresenterKey()
        }
    }

    protected fun getPresenterKey(): String {
        return mUniquePresenterKey
    }

    private fun createUniquePresenterKey(): String {
        return "${javaClass.simpleName}${System.currentTimeMillis()}"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_PRESENTER_KEY, getPresenterKey())
    }

}
