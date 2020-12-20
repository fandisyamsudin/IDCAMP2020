package com.bfaa.idchamp2020.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bfaa.idchamp2020.R
import com.bfaa.idchamp2020.databinding.ItemUserBinding
import com.bfaa.idchamp2020.model.UserGithub


class UserAdapter(
    private val onClickListener: OnClick
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private var userGithub: List<UserGithub> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int = userGithub.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userGithub[position])
    }

    inner class ViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(userGithub: UserGithub){
           binding.user = userGithub
            itemView.setOnClickListener{
                onClickListener.onUserClickListener(it, userGithub)
            }
        }
    }

    fun setList(list: List<UserGithub>) {
        this.userGithub = list
        notifyDataSetChanged()
    }

    interface OnClick {
        fun onUserClickListener(view: View, userGithub: UserGithub)
    }
}