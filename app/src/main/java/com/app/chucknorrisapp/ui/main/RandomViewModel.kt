package com.app.chucknorrisapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.chucknorrisapp.entity.RandomEntity
import com.app.chucknorrisapp.repository.random.RandomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class RandomViewModel : ViewModel(), CoroutineScope {

    private val repository = RandomRepository()
    val dataRandom = MutableLiveData<Response<RandomEntity>>()

    fun loadRandom(){
        launch {
            dataRandom.postValue(repository.loadRandom())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}

