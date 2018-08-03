package com.yelp.abrarsaair.yelpsearch.search.holder


import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.yelp.abrarsaair.yelpsearch.R
import com.yelp.abrarsaair.yelpsearch.convertCategory
import com.yelp.abrarsaair.yelpsearch.entities.search.Business

class BusinessViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title: TextView
    val reviewsCount: TextView
    val attributes: TextView
    val address: TextView
    val image: ImageView
    val rating: RatingBar


    init {
        // Define click listener for the ViewHolder's View.
        itemView.setOnClickListener {

        }
        title = itemView.findViewById(R.id.tv_business_name)
        reviewsCount = itemView.findViewById(R.id.reviews_count)
        attributes = itemView.findViewById(R.id.tv_attributes)
        address = itemView.findViewById(R.id.tv_address)
        image = itemView.findViewById(R.id.iv_business_pic)
        rating = itemView.findViewById(R.id.business_rating)
    }

    fun bind(business: Business) {
        title.text = business.name
        if (business.image_url.isNotEmpty()) {
            Picasso.with(image.context).load(business.image_url).placeholder(R.drawable.placeholder).into(image)
        }
        reviewsCount.text = "${business.review_count} reviews"
        rating.rating = business.rating.toFloat()
        address.text = business.location.display_address.joinToString { s -> s }
        attributes.text = convertCategory(business.categories)
    }

}