package com.ismin.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val books: ArrayList<Book>) : RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_book, parent, false)
        return BookViewHolder(row)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val (title, author, date) = this.books[position]

        holder.txvTitle.text = title
        holder.txvAuthor.text = author
        holder.txvDate.text = date
    }

    override fun getItemCount(): Int {
        return this.books.size
    }

    fun updateItem(booksToDisplay: List<Book>) {
        books.clear();
        books.addAll(booksToDisplay)
        notifyDataSetChanged();
    }
}
