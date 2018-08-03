package com.yelp.abrarsaair.yelpsearch.search.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yelp.abrarsaair.yelpsearch.R

import com.yelp.abrarsaair.yelpsearch.entities.search.Business
import com.yelp.abrarsaair.yelpsearch.search.holder.BusinessViewHolder
import com.yelp.abrarsaair.yelpsearch.search.holder.OpeningViewHolder

class SearchAdapter(private  val searchResults: List<Business>) : RecyclerView.Adapter<BusinessViewHolder>() {
    internal lateinit var itemClickListener: ItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {

        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.business_item, parent, false)
        return BusinessViewHolder(v)
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        holder.bind(searchResults.get(position))
        holder.itemView.setOnClickListener{
            itemClickListener?.onItemClick(searchResults.get(position))
        }
    }

    fun setOnItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun getItemCount(): Int {
        return searchResults.size
    }

    interface ItemClickListener {
        fun onItemClick(business: Business)
    }
}
