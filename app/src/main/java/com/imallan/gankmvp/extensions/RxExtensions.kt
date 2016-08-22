package com.imallan.gankmvp.extensions

import rx.Subscription
import rx.subscriptions.CompositeSubscription

fun Subscription.pending(compositeSubscription: CompositeSubscription): Subscription {
    compositeSubscription.add(this)
    return this
}
