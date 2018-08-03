package com.yelp.abrarsaair.yelpsearch.entities.search

import android.os.Parcel
import android.os.Parcelable
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion

class Category(val alias: String,val title: String) : SearchSuggestion{

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(alias)
        p0?.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun getBody(): String {
        return title
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }

}