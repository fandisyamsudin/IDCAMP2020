package com.jetpack.module012.ui.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.module012.R
import com.jetpack.module012.data.TVShow
import com.jetpack.module012.databinding.ItemListBinding

class TVAdapter : RecyclerView.Adapter<TVAdapter.TVViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private var listTV = ArrayList<TVShow>()

    fun setTVShows(tvShows: List<TVShow>?) {
        if (tvShows == null) return
        listTV.clear()
        listTV.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): TVViewHolder {
        return TVViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_list,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        val tv = listTV[position]
        holder.bind(tv)
    }

    override fun getItemCount(): Int {
        return listTV.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(tvShow: TVShow)
    }

    inner class TVViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShow) {
            binding.tv = tvShow
            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(tvShow)
            }
        }
    }
}