package com.imallan.gankmvp.extensions

import io.realm.Realm

fun Realm.inTransaction(func: Realm.() -> Unit, closeAfterTransaction: Boolean = false): Unit {
    beginTransaction()
    func()
    commitTransaction()
    if (closeAfterTransaction) close()
}
