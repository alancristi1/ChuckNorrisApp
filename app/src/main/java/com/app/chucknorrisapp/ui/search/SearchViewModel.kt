package com.app.chucknorrisapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chucknorrisapp.entity.FinderRandomEntity
import com.app.chucknorrisapp.entity.RandomEntity
import com.app.chucknorrisapp.repository.search.SearchRepository
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val repository : SearchRepository = SearchRepository()

    suspend fun finder(text : String) : Response<FinderRandomEntity>{
        return repository.findText(text)
    }
}