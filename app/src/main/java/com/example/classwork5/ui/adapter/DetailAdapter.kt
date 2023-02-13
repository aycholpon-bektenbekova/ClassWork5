package com.example.classwork5.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.classwork5.data.remote.model.Item
import com.example.classwork5.databinding.PlaylistItemBinding

class DetailAdapter:
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    private var items = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    fun setItemList(list: List<Item>) {
        items.addAll(list as ArrayList<Item>)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class DetailViewHolder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(items: Item) {

        }
    }
}