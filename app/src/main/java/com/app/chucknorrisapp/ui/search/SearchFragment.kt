package com.app.chucknorrisapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.chucknorrisapp.R
import com.app.chucknorrisapp.entity.FinderRandomEntity
import com.app.chucknorrisapp.ui.base.BaseFragment
import com.app.chucknorrisapp.ui.search.adapter.SearchAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class SearchFragment(private val navigation : BottomNavigationView) : BaseFragment() {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var searchAdapter : SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                loadManager(true)
                if(query!!.length >= 3){
                    viewModel.finder(query)

                    viewModel.dataFinder.observe(viewLifecycleOwner, { data ->
                        listSearch.adapter = SearchAdapter(data.body()!!.result)

                        listSearch.setHasFixedSize(true)

                        listSearch.layoutManager = LinearLayoutManager(
                            activity, LinearLayoutManager.VERTICAL, false
                        )

                        val size = data.body()!!.total
                        txt_findsize.text = "Resultados encontrados: $size"
                    })

                    if(listSearch.itemDecorationCount == 0){
                        listSearch.addItemDecoration(DividerItemDecoration(requireContext(),
                                DividerItemDecoration.VERTICAL))
                    }

                    loadManager(false)

                }else{
                    loadManager(false)
                    Toast.makeText(requireContext(), "A pesquisa precisa ter no mínimo 3 caracteres",
                            Toast.LENGTH_SHORT).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onResume() {
        super.onResume()
        loadManager(false)
    }

    private fun loadManager(start: Boolean){
        if(start){
            layoutLoad.visibility = View.VISIBLE
            navigation.visibility = View.GONE
            search_view.visibility = View.GONE
        }else{
            layoutLoad.visibility = View.GONE
            navigation.visibility = View.VISIBLE
            search_view.visibility = View.VISIBLE
        }
    }
}