package com.example.manpaginationroomrecycler.data.network

import com.example.manpaginationroomrecycler.domain.model.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainAPI {

    @GET("character")
    suspend fun getData(@Query("page") page: Int): Response<Data>
}