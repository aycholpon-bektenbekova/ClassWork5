package com.example.classwork5.ui.playlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.classwork5.R
import com.example.classwork5.databinding.PlaylistItemBinding
import com.example.classwork5.model.Item
import kotlin.reflect.KFunction1

class PlaylistAdapter(private var onItemClick: KFunction1<Item, Unit>):
    RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private var playlists = arrayListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    fun setPlayList(list: List<Item>) {
        playlists.addAll(list)
        notifyItemChanged(itemCount - 1)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.onBind(playlists[position])
    }

    override fun getItemCount(): Int = playlists.size

    inner class PlaylistViewHolder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(playlists: Item) {
            if (playlists.snippet.thumbnails.maxres?.url != null)
                Glide.with(binding.img).load(playlists.snippet.thumbnails.default.url)
                    .into(binding.img)
            binding.videoCount.text = itemView.context.getString(
                R.string.video_series,
                playlists.contentDetails.itemCount
            )
            binding.playlistName.text = playlists.snippet.title
            itemView.setOnClickListener {
               onItemClick
            }
        }
    }
}