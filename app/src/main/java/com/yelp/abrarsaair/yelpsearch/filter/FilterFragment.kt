package com.yelp.abrarsaair.yelpsearch.filter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.yelp.abrarsaair.yelpsearch.R

class FilterFragment : Fragment(), View.OnClickListener {


    private lateinit var seachByName: Button
    private lateinit var seachByAddress: Button
    private lateinit var seachByType: Button
    private lateinit var sortByRating: Button
    private lateinit var sortByDistance: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.filter_fragment, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        seachByName = view.findViewById(R.id.bt_business_name)
        seachByAddress = view.findViewById(R.id.bt_business_address)
        seachByType = view.findViewById(R.id.bt_business_type)

        sortByDistance = view.findViewById(R.id.bt_sort_distance)
        sortByRating = view.findViewById(R.id.bt_sort_rating)

        sortByDistance.setOnClickListener {
            onClick(it) }
        seachByName.setOnClickListener {
            onClick(it) }
        seachByAddress.setOnClickListener {
            onClick(it) }
        seachByType.setOnClickListener {
            onClick(it) }
        sortByRating.setOnClickListener {
            onClick(it) }

        setFilterValues()
    }

    private fun setFilterValues() {
        setSearchValues()
        setSortValues()
    }

    fun setSortValues() {
        sortByDistance.isSelected = false
        sortByRating.isSelected = false
        if (Filter.getInstance().sortType == Filter.SortType.distance) {
            sortByDistance.isSelected = true
        } else if (Filter.getInstance().sortType == Filter.SortType.rating) {
            sortByRating.isSelected = true
        }
    }

    fun setSearchValues() {
        seachByName.isSelected = false
        seachByAddress.isSelected = false
        seachByType.isSelected = false
        if (Filter.getInstance().searchType == Filter.SearchType.Name) {
            seachByName.isSelected = true
        } else if (Filter.getInstance().searchType == Filter.SearchType.Address) {
            seachByAddress.isSelected = true
        } else {
            seachByType.isSelected = true
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt_sort_rating -> {
                Filter.getInstance().sortType = Filter.SortType.rating
            }
            R.id.bt_sort_distance -> {
                Filter.getInstance().sortType = Filter.SortType.distance
            }
            R.id.bt_business_name -> {
            Filter.getInstance().searchType = Filter.SearchType.Name
            }
            R.id.bt_business_address -> {
                    Filter.getInstance().searchType = Filter.SearchType.Address
            }
            R.id.bt_business_type -> {
                Filter.getInstance().searchType = Filter.SearchType.Type
            }
        }
        setFilterValues()
    }

    companion object {

        @JvmStatic
        fun newInstance() = FilterFragment()
    }
}
