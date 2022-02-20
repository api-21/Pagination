package com.xpresscure.pagination.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xpresscure.pagination.Model.SearchResponse
import com.xpresscure.pagination.databinding.DogsLytBinding
import javax.inject.Inject

class DogsAdapter @Inject constructor() :
    PagingDataAdapter<SearchResponse, DogsAdapter.DogsVH>(DiffUitllCB) {

    class DogsVH(val binding: DogsLytBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchResponse) {

            Glide.with(binding.root.context).load(item.url).into(binding.dogsImg)
        }
    }


    override fun onBindViewHolder(holder: DogsVH, position: Int) {
        val dogs = getItem(position)
        if (dogs != null) {
            holder.bind(dogs)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsVH {
        return DogsVH(DogsLytBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    object DiffUitllCB : DiffUtil.ItemCallback<SearchResponse>() {
        override fun areItemsTheSame(oldItem: SearchResponse, newItem: SearchResponse): Boolean {
            return oldItem.url == newItem.url
        }
        override fun areContentsTheSame(oldItem: SearchResponse, newItem: SearchResponse): Boolean {
            return oldItem == newItem
        }
    }
}