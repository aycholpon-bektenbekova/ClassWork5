package com.example.classwork5.ui.playlists

import androidx.lifecycle.LiveData
import com.example.classwork5.App
import com.example.classwork5.core.network.result.Resource
import com.example.classwork5.core.ui.BaseViewModel
import com.example.classwork5.data.remote.model.Playlists

class PlaylistsViewModel: BaseViewModel() {

    fun getPlaylists(): LiveData<Resource<Playlists>>{
        return App().repository.getPlaylists()
    }
}