package com.yelp.abrarsaair.yelpsearch.search.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yelp.abrarsaair.yelpsearch.R
import com.yelp.abrarsaair.yelpsearch.entities.businessdetail.Open

import com.yelp.abrarsaair.yelpsearch.entities.search.Business
import com.yelp.abrarsaair.yelpsearch.search.holder.OpeningViewHolder

class OpeningAdapter(private  val openingHours: List<Open>) : RecyclerView.Adapter<OpeningViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpeningViewHolder {

        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.open_hours_item, parent, false)
        return OpeningViewHolder(v)
    }

    override fun onBindViewHolder(holder: OpeningViewHolder, position: Int) {
        holder.bind(openingHours.get(position))
    }

    override fun getItemCount(): Int {
        return openingHours.size
    }
}
