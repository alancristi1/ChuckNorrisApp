package com.app.chucknorrisapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chucknorrisapp.entity.FinderRandomEntity
import com.app.chucknorrisapp.entity.RandomEntity
import com.app.chucknorrisapp.repository.random.RandomRepository
import com.app.chucknorrisapp.repository.search.SearchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SearchViewModel : ViewModel(), CoroutineScope {

    private val repository = SearchRepository()
    val dataFinder = MutableLiveData<Response<FinderRandomEntity>>()

    fun finder(text : String) {
        launch {
            dataFinder.postValue(repository.findText(text))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}