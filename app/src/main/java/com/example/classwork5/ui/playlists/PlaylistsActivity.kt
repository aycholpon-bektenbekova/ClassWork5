package com.example.classwork5.ui.playlists

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.classwork5.core.network.result.Status
import com.example.classwork5.core.ui.BaseActivity
import com.example.classwork5.databinding.ActivityPlaylistsBinding
import com.example.classwork5.data.remote.model.Item
import com.example.classwork5.ui.adapter.PlaylistAdapter
import com.example.classwork5.ui.detail.DetailPlaylistActivity

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>() {
    private lateinit var adapter : PlaylistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          adapter = PlaylistAdapter(onItemClick = this::onItemClick)

    }
    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel = ViewModelProvider(this)[PlaylistsViewModel::class.java]
        viewModel.loading.observe(this){
            binding.loader.isVisible = it
        }
        setPlaylist()
    }

    private fun setPlaylist() {
        viewModel.getPlaylists().observe(this) {

            binding.rvPlaylists.layoutManager = LinearLayoutManager(this)
            binding.rvPlaylists.adapter = adapter

            when(it.status){
                Status.SUCCESS ->{
                    it.data?.let { it1 -> adapter.setPlayList(it1.items) }
                    viewModel.loading.value = false
                }
                Status.LOADING ->{
                    viewModel.loading.value = true
                }
                Status.ERROR ->{
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    viewModel.loading.value = false
                }
            }
        }
    }

    private fun onItemClick(list: Item) {
        Intent(this@PlaylistsActivity, DetailPlaylistActivity::class.java).apply {
            putExtra(ID, list.id)
            startActivity(this)
        }
    }

    companion object {
        const val ID = "id"
    }
}