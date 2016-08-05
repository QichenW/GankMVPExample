package com.imallan.playground

class APIResponse<T> {

    var error: Boolean = false
    var results: List<T> = mutableListOf()

}
