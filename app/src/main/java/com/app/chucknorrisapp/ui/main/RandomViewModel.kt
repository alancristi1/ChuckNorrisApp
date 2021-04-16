package com.app.chucknorrisapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chucknorrisapp.entity.RandomEntity
import com.app.chucknorrisapp.repository.random.RandomRepository
import retrofit2.Response

class RandomViewModel : ViewModel() {

    private val repository : RandomRepository = RandomRepository()
    val dataRandom = MutableLiveData<Response<RandomEntity>>()

    suspend fun loadRandom(){
        dataRandom.postValue(repository.loadRandom())
    }
}