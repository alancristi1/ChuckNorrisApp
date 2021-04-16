package com.app.chucknorrisapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.chucknorrisapp.R
import com.app.chucknorrisapp.entity.RandomEntity
import com.squareup.picasso.Picasso

class SearchAdapter(val data : List<RandomEntity>) : RecyclerView.Adapter<ViewHolderRow>(), Filterable {

    var resultSearch: MutableList<RandomEntity> = arrayListOf()
    var resultFilter : MutableList<RandomEntity> = arrayListOf()

    init {
        resultSearch = data as MutableList<RandomEntity>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRow {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ViewHolderRow(view)
    }

    override fun onBindViewHolder(holder: ViewHolderRow, position: Int) {
        holder.title.text = resultSearch[position].value

        Picasso.get()
                .load(resultSearch[position].icon_url)
//                .resize(150, 150)
//                .centerInside()
                .into(holder.image)
    }

    override fun getItemCount(): Int {
        return resultSearch.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if(constraint!!.isEmpty()){
                    resultSearch.clear()
                    resultFilter.clear()
                    notifyDataSetChanged()
                }else{
                    resultFilter.clear()
                    data.forEach {
                        resultFilter.add(it)
                        resultSearch = resultFilter
                    }
                }

                filterResults.values = resultSearch
                filterResults.count = resultSearch.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                resultSearch = results!!.values as MutableList<RandomEntity>
                notifyDataSetChanged()
            }
        }
    }
}

class ViewHolderRow(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title = itemView.findViewById<TextView>(R.id.txt_search)!!
    var image = itemView.findViewById<ImageView>(R.id.img_search)!!

//        val layoutEmpty = itemView.findViewById<ConstraintLayout>(R.id.layoutEmpty)
}