package com.example.classwork5.ui.playlist.detail

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.classwork5.base.BaseActivity
import com.example.classwork5.databinding.ActivityDetailPlaylistBinding
import com.example.classwork5.model.Item
import com.example.classwork5.ui.playlist.adapter.PlaylistAdapter

class DetailPlaylistActivity : BaseActivity<ActivityDetailPlaylistBinding,DViewModel>() {
    private lateinit var adapter : PlaylistAdapter

    private val id : String?
        get() = intent.getStringExtra(ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[DViewModel::class.java]
        adapter = PlaylistAdapter(onItemClick = this::onItemClick)
setPlaylist()
        Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()
    }
    private fun onItemClick(list: Item) {
        /*  Intent(this@PlaylistsActivity, DetailPlaylistActivity::class.java).apply {
            putExtra(ID, list.id)
            startActivity(this)
        }
    }*/
    }
    private fun setPlaylist() {
        id?.let {
            viewModel.item(it).observe(this) {

                binding.rvPlaylists.adapter = adapter

                adapter.setPlayList(it.items)

            }
        }
    }
    companion object {
        const val ID = "id"
    }
    override fun inflateViewBinding(): ActivityDetailPlaylistBinding {
        return ActivityDetailPlaylistBinding.inflate(layoutInflater)
    }
}