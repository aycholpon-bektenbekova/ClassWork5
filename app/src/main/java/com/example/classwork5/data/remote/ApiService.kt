package com.example.classwork5.data.remote

import com.example.classwork5.data.remote.model.Playlists
import com.example.classwork5.data.remote.model.PlaylistItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    fun getPlaylists(
        @Query("key") key: String ,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int,
       // @Query("pageToken") pageToken : String?
    ): Call<Playlists>
    @GET("playlistItems")
    fun getItemLists(
        @Query("key") key: String ,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int,
        @Query("playlistId") id:String
        // @Query("pageToken") pageToken : String?
    ): Call<PlaylistItem>
}