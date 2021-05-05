package com.app.chucknorrisapp.ui.main.framents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.chucknorrisapp.R
import com.app.chucknorrisapp.entity.RandomEntity
import com.app.chucknorrisapp.ui.base.BaseFragment
import com.app.chucknorrisapp.ui.main.RandomViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_random.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class RandomFragment(private val navigation: BottomNavigationView) : BaseFragment(){

    private val viewModel: RandomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadManager(true)

        viewModel.loadRandom()

        viewModel.dataRandom.observe(this.viewLifecycleOwner, { response ->
            when(response.code()){
                200 -> onSuccess(response.body())
                500 -> onError()
                else -> onError()
            }
        })

        btntry.setOnClickListener {
            loadManager(true)
            viewModel.loadRandom()
        }
    }

    private fun loadManager(start : Boolean){
        if(start){
            layoutLoad.visibility = View.VISIBLE
            navigation.visibility = View.GONE
            btntry.visibility = View.GONE
        }else{
            layoutLoad.visibility = View.GONE
            navigation.visibility = View.VISIBLE
            btntry.visibility = View.VISIBLE
        }
    }

    private fun onSuccess(body: RandomEntity?) {
        loadManager(false)
        Picasso.get()
            .load(body!!.icon_url)
            .resize(200, 200)
            .into(img_random, object : Callback{
                override fun onSuccess() {}

                override fun onError(e: Exception?) {
                    img_random.setImageResource(R.drawable.ic_error_24)
                }
            })

        text_random.text = body.value
    }

    private fun onError(){

    }
}