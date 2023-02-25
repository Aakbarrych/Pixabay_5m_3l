package com.example.pixabay_5m_3l

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {

    @GET("api/")
    fun getImages(
        @Query("key") key: String = "33902499-695d605e96032c389101f8f63",
        @Query("q") pictureWord: String,
        @Query("per_page") perPage: Int = 3,
        @Query("page") page: Int,
    ): Call<PixaModel>
}