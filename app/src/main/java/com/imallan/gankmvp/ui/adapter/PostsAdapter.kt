package com.imallan.gankmvp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.imallan.gankmvp.R
import com.imallan.gankmvp.extensions.loadUrl
import com.imallan.playground.Post
import com.imallan.toothpick.OnClick
import com.imallan.toothpick.Toothpick
import com.imallan.toothpick.bind

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private var mData: List<Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = mData[position]
        holder.mPost = post
        holder.mImagePost.loadUrl(post.url)
    }

    override fun getItemCount(): Int = mData.size

    fun setData(data: List<Post>) {
        mData = data
        notifyDataSetChanged()
    }

    class ViewHolder : RecyclerView.ViewHolder {

        val mImagePost: ImageView by bind(R.id.image_post, itemView)
        var mPost: Post? = null

        constructor(itemView: View) : super(itemView) {
            Toothpick.bind(this, itemView)
        }

        @OnClick(R.id.image_post)
        fun showDesc() {
            Toast.makeText(itemView.context, "${mPost?.desc ?: null}", Toast.LENGTH_SHORT).show()
        }
    }

}
