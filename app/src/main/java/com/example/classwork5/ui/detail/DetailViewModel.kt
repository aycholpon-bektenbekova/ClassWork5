package com.example.classwork5.ui.detail

import androidx.lifecycle.LiveData
import com.example.classwork5.App
import com.example.classwork5.core.ui.BaseViewModel
import com.example.classwork5.data.remote.model.PlaylistItem

class DetailViewModel: BaseViewModel() {

    fun getItemLists(id: String): LiveData<PlaylistItem> {
        return App().repository.getItemList(id = String())
    }
}