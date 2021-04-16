package com.app.chucknorrisapp.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chucknorrisapp.entity.RandomEntity
import com.app.chucknorrisapp.repository.category.CategoryRepository
import retrofit2.Response

class CategoryViewModel : ViewModel(){
    private val repository : CategoryRepository = CategoryRepository()
    val dataCategory = MutableLiveData<Response<List<String>>>()
    val randomByCategory = MutableLiveData<Response<RandomEntity>>()

    suspend fun loadCategory(){
        dataCategory.postValue(repository.loadCategory())
    }

    suspend fun loadRandomByCategory(text : String){
        randomByCategory.postValue(repository.randomByCategory(text))
    }
}