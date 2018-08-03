package com.yelp.abrarsaair.yelpsearch.search.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.yelp.abrarsaair.yelpsearch.R
import com.yelp.abrarsaair.yelpsearch.entities.businessdetail.Open


class OpeningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var weekdays = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    val weekDay: TextView
    val timing: TextView
    val overNight: TextView

    init {
        weekDay = itemView.findViewById(R.id.week_day)
        overNight = itemView.findViewById(R.id.is_overNight)
        timing = itemView.findViewById(R.id.timing)
    }

    fun bind(openingHour: Open) {
        weekDay.text = weekdays[openingHour.day]
        timing.text = formatOpeningTime(openingHour)
        if(openingHour.is_overnight) {
            overNight.text =  "Overnight"
        }else{
            overNight.text =  ""
        }
    }

    private fun formatOpeningTime(openingHour: Open): String {
        val start = openingHour.start
        val midStart = start.length / 2 //get the middle of the String
        val partsStart = arrayOf(start.substring(0, midStart), start.substring(midStart)) .joinToString(":")

        val end = openingHour.end
         val mid = end.length / 2 //get the middle of the String
         val parts = arrayOf(end.substring(0, mid), end.substring(mid)).joinToString(":")
        return "$partsStart - $parts"
    }

}