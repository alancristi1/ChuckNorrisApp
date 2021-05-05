package com.app.chucknorrisapp.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chucknorrisapp.entity.RandomEntity
import com.app.chucknorrisapp.repository.category.CategoryRepository
import com.app.chucknorrisapp.repository.random.RandomRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CategoryViewModel : ViewModel(), CoroutineScope {

    private val repository = CategoryRepository()
    val loadCategoryData = MutableLiveData<Response<List<String>>>()
    val loadRandomByCategoryData = MutableLiveData<Response<RandomEntity>>()

    fun loadCategory(){
        launch {
            loadCategoryData.postValue(repository.loadCategory())
        }
    }

    fun loadRandomByCategory(text : String){
        launch {
            loadRandomByCategoryData.postValue(repository.randomByCategory(text))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}