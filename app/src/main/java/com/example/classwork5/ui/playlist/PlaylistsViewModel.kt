package com.example.classwork5.ui.playlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.classwork5.BuildConfig
import com.example.classwork5.`object`.Constant
import com.example.classwork5.base.BaseViewModel
import com.example.classwork5.model.Playlist
import com.example.classwork5.remote.ApiService
import com.example.classwork5.remote.RetrofitClient
import com.example.classwork5.ui.playlist.adapter.PlaylistAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistsViewModel: BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun playlists(): LiveData<Playlist>{
        return getPlaylists()
    }


    private fun getPlaylists(): LiveData<Playlist> {
        val data = MutableLiveData<Playlist>()
        apiService.getPlaylists(BuildConfig.API_KEY, Constant.part, Constant.channelId,Constant.maxResults
           )
            .enqueue(object : Callback<Playlist>{
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful) {
                        data.value = response.body()

                        Log.e("ololo", "onResponse:${response.body()} ", )
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    print(t.stackTrace)
                    Log.e("ololo", "error ", )

                }

            })
        return data
    }

}