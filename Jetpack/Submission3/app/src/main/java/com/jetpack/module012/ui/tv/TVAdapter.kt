package com.jetpack.module012.ui.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jetpack.module012.R
import com.jetpack.module012.data.room.TVShowEntity
import com.jetpack.module012.databinding.ItemListBinding

class TVAdapter : PagedListAdapter<TVShowEntity, TVAdapter.TVViewHolder>(DIFF_CALLBACK) {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVShowEntity>() {
            override fun areItemsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVShowEntity, newItem: TVShowEntity): Boolean {
                return oldItem == newItem
            }
        }
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
        val tv = getItem(position)
        tv?.let { holder.bind(it) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(tvShow: TVShowEntity)
    }

    inner class TVViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShowEntity) {
            binding.tv = tvShow
            itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(tvShow)
            }
        }
    }
}