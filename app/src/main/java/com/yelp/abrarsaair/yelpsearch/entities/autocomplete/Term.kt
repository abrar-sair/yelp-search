package com.yelp.abrarsaair.yelpsearch.entities.autocomplete

import android.os.Parcel
import android.os.Parcelable
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion

class Term(val text: String) : SearchSuggestion{


    override fun describeContents(): Int {
      return 0
    }

    override fun getBody(): String {
        return text
    }

    companion object CREATOR : Parcelable.Creator<Term> {
        override fun createFromParcel(parcel: Parcel): Term {
            return Term(parcel.toString())
        }

        override fun newArray(size: Int): Array<Term?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(text)

    }

}