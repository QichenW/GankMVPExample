package com.imallan.gankmvp

import com.imallan.gankmvp.extensions.logd
import java.util.*

object PresenterManager {

    private val map = HashMap<String, Any>()

    private fun put(key: String, presenter: Any) {
        map.put(key, presenter)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String, getter: () -> T): T {
        if (map.contains(key)) {
            logd("Presenter map hit: $key")
            return map[key] as T
        }
        logd("Miss. Creating new: $key")
        val t = getter()
        if (t != null) put(key, t)
        return t
    }

    fun remove(key: String) {
        map.remove(key)
    }

}
