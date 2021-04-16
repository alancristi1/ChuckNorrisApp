package com.app.chucknorrisapp.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.app.chucknorrisapp.R
import com.app.chucknorrisapp.ui.base.BaseFragment
import com.app.chucknorrisapp.ui.category.adapter.SpinnerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CategoryFragment(private val navigation: BottomNavigationView) : BaseFragment(),
    CoroutineScope {

    private val viewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadManager(true)
        launch {
            viewModel.loadCategory()
        }

        viewModel.dataCategory.observe(this.viewLifecycleOwner, {
            when(it.code()){
                200 -> loadSpinner(it.body()!!)
                else -> onError()

            }
        })
    }

    private fun loadSpinner(data : List<String>){

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item, data)

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)

        spinner.adapter = SpinnerAdapter(requireContext(), data as ArrayList<String>)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val itemSelect = data[position]
                launch {
                    viewModel.loadRandomByCategory(itemSelect)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        viewModel.randomByCategory.observe(this.viewLifecycleOwner, { data ->

            Picasso.get()
                .load(data.body()!!.icon_url)
                .resize(200, 200)
                .into(img_category, object : Callback {
                    override fun onSuccess() {}

                    override fun onError(e: Exception?) {
                        img_category.setImageResource(R.drawable.ic_error_24)
                    }
                })

            txt_phrase.text = data.body()!!.value
            loadManager(false)
        })
    }

    private fun onError(){}

    private fun loadManager(start: Boolean){
        if(start){
            layoutLoad.visibility = View.VISIBLE
            navigation.visibility = View.GONE
        }else{
            layoutLoad.visibility = View.GONE
            navigation.visibility = View.VISIBLE
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}