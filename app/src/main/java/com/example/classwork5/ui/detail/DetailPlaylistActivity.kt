package com.example.classwork5.ui.detail

import androidx.lifecycle.ViewModelProvider
import com.example.classwork5.core.ui.BaseActivity
import com.example.classwork5.databinding.ActivityDetailPlaylistBinding
import com.example.classwork5.ui.adapter.DetailAdapter

class DetailPlaylistActivity : BaseActivity<ActivityDetailPlaylistBinding, DetailViewModel>() {
    private lateinit var adapter : DetailAdapter

    private val id : String?
        get() = intent.getStringExtra(ID)

    override fun inflateViewBinding(): ActivityDetailPlaylistBinding {
        return ActivityDetailPlaylistBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        setItemList()
    }

    private fun setItemList() {
        id?.let { it ->
            viewModel.getItemLists(id = it).observe(this) {
                binding.detailRv.adapter = adapter

                adapter.setItemList(it.items)
            }
        }
    }
    companion object {
        const val ID = "id"
    }
}