package com.ismin.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class BookAdapter(private var books: ArrayList<Book>, private val favoriteListener: onFavoriteListener) : RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_book, parent, false)
        return BookViewHolder(row)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val (title, author, date) = this.books[position]

        holder.txvTitle.text = title
        holder.txvAuthor.text = author
        holder.txvDate.text = date

        holder.unfavButton.setOnClickListener{
            favoriteListener.unfavFavorite(position)
            holder.unfavButton.visibility = View.GONE
            holder.favButton.visibility = View.VISIBLE
        }
        holder.favButton.setOnClickListener{
            favoriteListener.unfavFavorite(position)
            holder.unfavButton.visibility = View.VISIBLE
            holder.favButton.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return this.books.size
    }

   fun updateItem(booksToDisplay: List<Book>) {
        books.clear();
        books.addAll(booksToDisplay)
        notifyDataSetChanged();
    }

    fun updateList(bookList: ArrayList<Book>){
        books = bookList
        notifyDataSetChanged()
    }


}
