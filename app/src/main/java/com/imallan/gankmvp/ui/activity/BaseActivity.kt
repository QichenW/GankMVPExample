package com.imallan.gankmvp.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.imallan.gankmvp.PresenterManager

open class BaseActivity : AppCompatActivity() {

    companion object {
        const val STATE_PRESENTER_KEY = "STATE_PRESENTER_KEY"
    }

    lateinit var componentKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        componentKey = if (savedInstanceState != null) {
            savedInstanceState.getString(STATE_PRESENTER_KEY) ?: createUniqueComponentKey()
        } else {
            createUniqueComponentKey()
        }
    }

    private fun createUniqueComponentKey(): String {
        return "${javaClass.simpleName}${System.currentTimeMillis()}"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_PRESENTER_KEY, componentKey)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) PresenterManager.remove(componentKey)
    }

}
