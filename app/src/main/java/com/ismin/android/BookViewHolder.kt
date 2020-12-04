package com.ismin.android

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txvTitle = itemView.findViewById<TextView>(R.id.r_book_txv_title)
    var txvAuthor: TextView = itemView.findViewById(R.id.r_book_txv_author)
    var txvDate: TextView = itemView.findViewById(R.id.r_book_txv_date)
    var unfavButton: ImageButton = itemView.findViewById(R.id.f_unfavorite_button)
    var favButton: ImageButton = itemView.findViewById(R.id.f_favorite_button)
}