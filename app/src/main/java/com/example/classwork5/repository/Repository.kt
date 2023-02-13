package com.example.classwork5.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.classwork5.data.remote.ApiService
import com.example.classwork5.core.network.RetrofitClient
import com.example.classwork5.core.network.result.Resource
import com.example.classwork5.data.remote.model.Playlists
import com.example.classwork5.utils.Constant
import com.example.classwork5.BuildConfig
import com.example.classwork5.data.remote.model.PlaylistItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlaylists(): LiveData<Resource<Playlists>> {
        val data = MutableLiveData<Resource<Playlists>>()

        data.value = Resource.loading()
        apiService.getPlaylists(
            BuildConfig.API_KEY,
            Constant.part,
            Constant.channelId,
            Constant.maxResults
        )
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    if (response.isSuccessful) {
                        data.value = Resource.success(response.body())
                    }
                }

                override fun onFailure(call: Call<Playlists>, t: Throwable) {
                    print(t.stackTrace)

                    data.value = Resource.error(t.stackTrace.toString(), null, null)
                }

            })
        return data
    }

     fun getItemList(id:String): LiveData<PlaylistItem> {
        val data = MutableLiveData<PlaylistItem>()
        apiService.getItemLists(
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