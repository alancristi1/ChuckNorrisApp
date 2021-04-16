package com.app.chucknorrisapp.networking

import com.app.chucknorrisapp.entity.FinderRandomEntity
import com.app.chucknorrisapp.entity.RandomEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/jokes/random")
    suspend fun requestOfRandom() : Response<RandomEntity>

    @GET("/jokes/categories")
    suspend fun requestOfCategory() : Response<List<String>>

    @GET("/jokes/search")
    suspend fun requestFilter(@Query("query") text : String) : Response<FinderRandomEntity>

    @GET("/jokes/random?")
    suspend fun requestRandomByCategory(@Query("category") text : String) : Response<RandomEntity>
}