package com.app.chucknorrisapp.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.chucknorrisapp.R
import com.app.chucknorrisapp.entity.RandomEntity
import com.app.chucknorrisapp.ui.category.CategoryFragment
import com.app.chucknorrisapp.ui.main.framents.RandomFragment
import com.app.chucknorrisapp.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(RandomFragment(navigation_view))

        navigation_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_random -> {
                    replaceFragment(RandomFragment(navigation_view))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_search -> {
                    replaceFragment(SearchFragment(navigation_view))
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_category -> {
                    replaceFragment(CategoryFragment(navigation_view))
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.root_layout, fragment)
        fragmentTransaction.commit()
    }
}