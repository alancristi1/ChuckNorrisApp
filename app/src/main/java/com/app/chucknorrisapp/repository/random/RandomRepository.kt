package com.app.chucknorrisapp.repository.random

import androidx.annotation.UiThread
import com.app.chucknorrisapp.entity.RandomEntity
import com.app.chucknorrisapp.networking.RetrofitBuilder
import retrofit2.Response

class RandomRepository {

    @UiThread
    suspend fun loadRandom(): Response<RandomEntity> {
        return RetrofitBuilder.apiInterface.requestOfRandom()
    }

    @UiThread
    suspend fun loadCategory(): Response<List<String>> {
        return RetrofitBuilder.apiInterface.requestOfCategory()
    }
}