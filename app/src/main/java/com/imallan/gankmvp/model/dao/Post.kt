package com.imallan.playground

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Post() : RealmObject() {

    @PrimaryKey lateinit var _id: String
    var createdAt: String? = null
    var publishedAt: String? = null
    lateinit var type: String
    lateinit var url: String
    var desc: String? = null

}

