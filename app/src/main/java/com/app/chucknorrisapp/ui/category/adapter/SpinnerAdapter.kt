package com.app.chucknorrisapp.ui.category.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.app.chucknorrisapp.R
import kotlinx.android.synthetic.main.custom_spinner.view.*

class SpinnerAdapter(context : Context, var data : ArrayList<String>) : BaseAdapter(){

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.custom_spinner, parent, false)

        view.name_category.text = data[position]
        return view
    }
}