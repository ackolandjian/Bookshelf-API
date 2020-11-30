package com.ismin.android

import android.content.ClipData
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_BOOKS = "ARG_BOOKS"

class BookListFragment : Fragment(), onFavoriteListener {
    private lateinit var books: ArrayList<Book>
    private lateinit var rcvBooks: RecyclerView
    private lateinit var adapter: BookAdapter
    private val favoriteBooks = arrayListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            books = it.getSerializable(ARG_BOOKS) as ArrayList<Book>
        }
    }

    override fun unfavFavorite(index: Int) {
        if(books[index] in favoriteBooks)
            favoriteBooks.remove(books[index])
        else
            favoriteBooks.add(books[index])
        Toast.makeText(context, books[index].title, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_book_list, container, false)
        val searchField: EditText = rootView.findViewById(R.id.f_book_list_search)


        this.rcvBooks = rootView.findViewById(R.id.f_book_list_rcv_books)
        this.adapter = BookAdapter(books, this)
        this.rcvBooks.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(context)
        this.rcvBooks.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        this.rcvBooks.addItemDecoration(dividerItemDecoration)


        searchField.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })

        return rootView;
    }

    companion object {
        @JvmStatic
        fun newInstance(books: List<Book>) =
            BookListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_BOOKS, ArrayList(books))
                }
            }
    }

    fun filter(text: String) {
        val temp = arrayListOf<Book>()
        var lowerText = text.toLowerCase()
        for (d in books) {
            if (d.title.toLowerCase().contains(lowerText) ||
                d.author.toLowerCase().contains(lowerText) ||
                d.date.toLowerCase().contains(lowerText)) {
                temp.add(d)
            }
        }
        this.adapter.updateList(temp)
    }

}



