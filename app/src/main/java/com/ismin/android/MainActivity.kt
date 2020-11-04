package com.ismin.android

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), BookCreator {
    private val TAG = MainActivity::class.simpleName
    private val bookshelf = Bookshelf()
    private lateinit var bookService: BookService;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://bookshelf-gme.cleverapps.io")
            .build()

        bookService = retrofit.create(BookService::class.java)

        bookService.getAllBooks().enqueue(object : Callback<ArrayList<Book>> {
            override fun onResponse(
                call: Call<ArrayList<Book>>,
                response: Response<ArrayList<Book>>
            ) {
                val allBooks = response.body()
                allBooks?.forEach {
                    bookshelf.addBook(it)
                }

                displayList()
            }

            override fun onFailure(call: Call<ArrayList<Book>>, t: Throwable) {
                displayErrorToast(t)

            }
        })

    }

    private fun displayErrorToast(t: Throwable) {
        Toast.makeText(
            applicationContext,
            "Network error ${t.localizedMessage}",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun displayList() {
        val bookListFragment = BookListFragment.newInstance(bookshelf.getAllBooks())

        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_lyt_container, bookListFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

    fun goToCreation(view: View) {
        val createBookFragment = CreateBookFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.a_main_lyt_container, createBookFragment)
            .addToBackStack("createBookFragment")
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

        a_main_btn_creation.visibility = View.GONE
    }

    override fun onBookCreated(book: Book) {
        bookService.createBook(book).enqueue {
            onResponse = {
                bookshelf.addBook(it.body()!!)
                closeCreateFragment()
            }
            onFailure = {
                if (it != null) {
                    displayErrorToast(it)
                }
            }
        }
    }

    override fun closeCreateFragment() {
        displayList();

        a_main_btn_creation.visibility = View.VISIBLE
    }
}