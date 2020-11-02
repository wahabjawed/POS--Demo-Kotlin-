package com.coolblue.pos.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CoolBlueAPI {



    @GET("search")
    suspend fun searchProducts(
        @Query("query") query: String,
        @Query("page") page: Int
    ): ProductResponse

}