package com.app.chucknorrisapp.repository.search

import androidx.annotation.UiThread
import com.app.chucknorrisapp.entity.FinderRandomEntity
import com.app.chucknorrisapp.networking.ApiService
import com.app.chucknorrisapp.networking.RetrofitBuilder
import retrofit2.Response
import javax.inject.Inject

class SearchRepository{

    @UiThread
    suspend fun findText(text : String): Response<FinderRandomEntity> {
        return RetrofitBuilder.apiInterface.requestFilter(text)
    }
}