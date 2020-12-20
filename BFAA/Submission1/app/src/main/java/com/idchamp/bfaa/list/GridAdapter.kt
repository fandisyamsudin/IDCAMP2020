package com.idchamp.bfaa.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.idchamp.bfaa.R
import kotlinx.android.synthetic.main.item_card_user.view.*

class GridAdapter(private val listUser: ArrayList<Users>) : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(users: Users) {
            with(itemView){
                Glide.with(itemView.context)
                    .load(users.avatar)
                    .apply(RequestOptions().override(140, 160))
                    .into(img_user)
                tv_name_user.text = users.name
                itemView.setOnClickListener{
                    onItemClickCallback?.onItemClicked(users)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_user, parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Users)
    }
}