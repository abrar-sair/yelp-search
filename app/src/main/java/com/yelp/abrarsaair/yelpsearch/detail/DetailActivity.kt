package com.yelp.abrarsaair.yelpsearch.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.yelp.abrarsaair.yelpsearch.Const
import com.yelp.abrarsaair.yelpsearch.R
import com.yelp.abrarsaair.yelpsearch.convertCategory
import com.yelp.abrarsaair.yelpsearch.entities.businessdetail.Detail
import com.yelp.abrarsaair.yelpsearch.entities.search.Category
import com.yelp.abrarsaair.yelpsearch.search.adapter.OpeningAdapter


class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var businessId: String
    private lateinit var businessImage: ImageView
    private lateinit var businessName: TextView
    private lateinit var businessAddress: TextView
    private lateinit var businessRating: RatingBar
    private lateinit var reviewCount: TextView
    private lateinit var businessCategories: TextView
    private lateinit var holder: LinearLayout
    private lateinit var businessContact: TextView
    private lateinit var businesslink: TextView
    private lateinit var businessPrice: TextView
    private lateinit var openingList: RecyclerView
    private lateinit var openNow: TextView
    private lateinit var presenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        readExtras(intent.extras)
        init()
        presenter = DetailPresenter()
        presenter.attachView(this)
        presenter.loadBusinessDetail(businessId)
    }

    private fun readExtras(extras: Bundle?) {
        businessId = extras?.getString(Const.BUSINESS_ID)!!
    }

    override fun showError(error: Error) {
    }



    override fun  init() {
        holder = findViewById(R.id.detail_holder)
        businessName = findViewById(R.id.business_name)
        businessImage = findViewById(R.id.business_image)
        businessAddress = findViewById(R.id.business_address)
        businessRating = findViewById(R.id.rating_bar)
        reviewCount = findViewById(R.id.reviews_count)
        businessCategories = findViewById(R.id.business_categories)
        businessContact = findViewById(R.id.business_contact)
        businesslink = findViewById(R.id.business_link)
        businessPrice = findViewById(R.id.business_price)
        openingList = findViewById(R.id.opening_hours_list)
        openNow = findViewById(R.id.is_open)
    }

    override fun showBusinessDetail(detail: Detail) {
        holder.visibility = View.VISIBLE
        businessName.text = detail.name
        if (detail.image_url.isNotEmpty()) {
            Picasso.with(businessImage.context).load(detail.image_url).placeholder(R.drawable.placeholder).into(businessImage)
        }
        businessAddress.text = detail.location.display_address.joinToString { s -> s }
        populateBusinessCategories(detail.categories)
        businessRating.rating = detail.rating.toFloat()
        reviewCount.text = "${detail.review_count} reviews"
        if (detail.display_phone.isNotEmpty()) {
            businessContact.text = detail.display_phone
        } else {
            businessContact.text = detail.phone
        }
        businesslink.text = detail.url
        businessPrice.text = detail.price
        populateOpeningHours(detail)
    }

    private fun populateOpeningHours(detail: Detail) {
        if(detail.hours.isNotEmpty()){
            openNow.visibility = if (detail.hours.get(0).is_open_now) View.VISIBLE else View.GONE
            val open = detail.hours.get(0).open
            val adapter = OpeningAdapter(open)
            val layoutManager = LinearLayoutManager(this)
            openingList.layoutManager = layoutManager
            openingList.adapter = adapter;
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun populateBusinessCategories(categories: List<Category>) {
        val cat = convertCategory(categories)
        businessCategories.text = cat
    }
}
