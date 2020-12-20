package com.jetpack.module012.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.module012.R
import com.jetpack.module012.data.TVShow
import kotlinx.android.synthetic.main.item_list.view.*

class TVAdapter: RecyclerView.Adapter<TVAdapter.TVViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private var listTV = ArrayList<TVShow>()

    fun setTVShows(tvShows: List<TVShow>?) {
        if (tvShows == null) return
        listTV.clear()
        listTV.addAll(tvShows)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TVViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return TVViewHolder(view)
    }

    override fun onBindViewHolder(holder: TVAdapter.TVViewHolder, position: Int) {
        val tv = listTV[position]
        holder.bind(tv)
    }

    override fun getItemCount(): Int {
        return listTV.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(tvShow: TVShow)
    }

    inner class TVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: TVShow){
            with(itemView){
                tv_title.text = tvShow.title
                tv_release.text = tvShow.release

                Glide.with(itemView.context)
                    .load(tvShow.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.poster_aquaman))
                    .error(R.drawable.ic_error)
                    .into(iv_poster)

                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(tvShow)
                }
            }
        }
    }
}