package com.xpresscure.pagination.Retrofit

import com.xpresscure.pagination.Model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.thedogapi.com"
    }

    @GET("v1/images/search")
    suspend fun GetSearchItem(
        @Query("page") page: Int,
        @Query("limit") limit : Int): List<SearchResponse>
}