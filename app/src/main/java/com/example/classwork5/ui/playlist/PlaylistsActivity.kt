package com.example.classwork5.ui.playlist

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.classwork5.base.BaseActivity
import com.example.classwork5.databinding.ActivityPlaylistsBinding
import com.example.classwork5.model.Item
import com.example.classwork5.ui.playlist.adapter.PlaylistAdapter
import com.example.classwork5.ui.playlist.detail.DetailPlaylistActivity

class PlaylistsActivity : BaseActivity<ActivityPlaylistsBinding, PlaylistsViewModel>() {
    private var adapter = PlaylistAdapter(onItemClick = this::onItemClick)

    private var totalCount: Int = 1
    private lateinit var pageToken: String
    override fun inflateViewBinding(): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel = ViewModelProvider(this)[PlaylistsViewModel::class.java]

        setPlaylist()

    }

    override fun initView() {
        super.initView()
        binding.rvPlaylists.adapter = adapter
    }

    private fun setPlaylist() {
        viewModel.playlists().observe(this) {
            pageToken = it.nextPageToken
            totalCount = it.pageInfo.totalResults
            adapter.setPlayList(it.items)

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