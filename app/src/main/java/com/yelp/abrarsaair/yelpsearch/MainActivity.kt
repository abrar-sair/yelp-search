package com.yelp.abrarsaair.yelpsearch

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.yelp.abrarsaair.yelpsearch.core.location.LocationProvider
import com.yelp.abrarsaair.yelpsearch.detail.DetailActivity
import com.yelp.abrarsaair.yelpsearch.entities.search.Business
import com.yelp.abrarsaair.yelpsearch.filter.FilterFragment
import com.yelp.abrarsaair.yelpsearch.search.SearchContract
import com.yelp.abrarsaair.yelpsearch.search.SearchPresenter
import com.yelp.abrarsaair.yelpsearch.search.adapter.OpeningAdapter
import com.yelp.abrarsaair.yelpsearch.search.adapter.SearchAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SearchContract.View, SearchAdapter.ItemClickListener {

    private lateinit var presenter: SearchContract.Presenter
    private lateinit var floatingSearchView: FloatingSearchView
    private lateinit var businessList: RecyclerView
    private lateinit var drawerLayout: DrawerLayout;
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        context = this
        presenter = SearchPresenter()
        presenter.attachView(this)
        init()
    }

    override fun init() {
        LocationProvider.getInstance(this).startLocationTask()
        floatingSearchView = findViewById(R.id.floating_search_view)
        businessList = findViewById(R.id.business_list)
        drawerLayout = findViewById(R.id.drawer_layout)
        handleAutoComplete()
        handleSearch()
        handleFilter()
        handleDrawerEvents()
    }

    private fun handleDrawerEvents() {
        supportFragmentManager.beginTransaction().replace(R.id.filter_view,
                FilterFragment.newInstance(),
                "filter").commit();
        drawerLayout.addDrawerListener(
                object : DrawerLayout.DrawerListener {
                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

                    override fun onDrawerOpened(drawerView: View) {
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                    }

                    override fun onDrawerClosed(drawerView: View) {
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    }

                    override fun onDrawerStateChanged(newState: Int) {}
                }
        )

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun handleFilter() {
        floatingSearchView.setOnMenuItemClickListener(object : FloatingSearchView.OnMenuItemClickListener {
            override fun onActionMenuItemSelected(item: MenuItem?) {
                showFilterView()
            }
        })
    }

    private fun showFilterView() {
        drawerLayout.openDrawer(Gravity.END);
    }

    override fun hideProgress() {
        floatingSearchView.hideProgress()
    }

    private fun handleSearch() {
        floatingSearchView.setOnSearchListener(object : FloatingSearchView.OnSearchListener {
            override fun onSuggestionClicked(searchSuggestion: SearchSuggestion) {
                presenter.loadBusiness(searchSuggestion.body, LocationProvider.getInstance(context).currentLocation!!)
                floatingSearchView.setSearchFocused(false)

            }

            override fun onSearchAction(query: String) {
                presenter.loadBusiness(query, LocationProvider.getInstance(context).currentLocation!!)
            }
        })
    }

    override fun showSearchResults(result: List<Business>) {
        val adapter = SearchAdapter(result)
        val layoutManager = LinearLayoutManager(context)
        adapter.setOnItemClickListener(this)
        businessList.layoutManager = layoutManager
        businessList.adapter = adapter;
    }

    private fun handleAutoComplete() {
        floatingSearchView.setOnQueryChangeListener { _, newQuery ->
            //use rx java to deffer change and then hit api and map result
            floating_search_view.showProgress()
            presenter.loadSuggestions(newQuery, LocationProvider.getInstance(context).currentLocation!!);
        };
    }

    override fun updateSuggestions(result: List<SearchSuggestion>) {
        floatingSearchView.swapSuggestions(result)
    }

    override fun showError(error: Error) {
        floatingSearchView.hideProgress()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Const.REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    LocationProvider.getInstance(this).startLocationTask()
                }
            }
        }
    }

    override fun onItemClick(business: Business) {
        showDetail(business)
    }

    override fun showDetail(business: Business) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Const.BUSINESS_ID, business.id)
        startActivity(intent)

    }

}
