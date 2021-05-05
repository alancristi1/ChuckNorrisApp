package com.app.chucknorrisapp.repository.category

import androidx.annotation.UiThread
import com.app.chucknorrisapp.entity.RandomEntity
import com.app.chucknorrisapp.networking.ApiService
import com.app.chucknorrisapp.networking.RetrofitBuilder
import retrofit2.Response
import javax.inject.Inject

class CategoryRepository {

    @UiThread
    suspend fun loadCategory(): Response<List<String>> {
        return RetrofitBuilder.apiInterface.requestOfCategory()
    }

    @UiThread
    suspend fun randomByCategory(text : String): Response<RandomEntity> {
        return RetrofitBuilder.apiInterface.requestRandomByCategory(text)
    }

}