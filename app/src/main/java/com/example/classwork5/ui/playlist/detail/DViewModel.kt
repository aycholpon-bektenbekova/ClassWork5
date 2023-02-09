package com.example.classwork5.ui.playlist.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.classwork5.BuildConfig
import com.example.classwork5.`object`.Constant
import com.example.classwork5.base.BaseViewModel
import com.example.classwork5.model.PlaylistItem
import com.example.classwork5.remote.ApiService
import com.example.classwork5.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DViewModel:BaseViewModel() {
    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }
    fun item(id: String): LiveData<PlaylistItem> {
        return getPlaylistItemList(id)
    }
    private fun getPlaylistItemList(id:String): MutableLiveData<PlaylistItem> {
        val data = MutableLiveData<PlaylistItem>()
        apiService.getItemlists(
            BuildConfig.API_KEY, Constant.part, Constant.channelId, Constant.maxResults,id)
            .enqueue(object : Callback<PlaylistItem> {
                override fun onResponse(call: Call<PlaylistItem>, response: Response<PlaylistItem>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<PlaylistItem>, t: Throwable) {
                    print(t.stackTrace)
                }

            })
        return data
    }
}