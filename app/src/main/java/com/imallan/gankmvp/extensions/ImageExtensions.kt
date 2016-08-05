package com.imallan.gankmvp.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String?) {
    Picasso.with(context).load(url).fit().centerCrop().into(this)
}
