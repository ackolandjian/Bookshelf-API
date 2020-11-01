package com.ismin.android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), BookCreator {
    private val TAG = MainActivity::class.simpleName

    private val bookshelf = Bookshelf()
    private val theLordOfTheRings = Book(
        title = "The Lord of the Rings",
        author = "J. R. R. Tolkien",
        date = "1954-02-15"
    )

    private val theHobbit = Book(
        title = "The Hobbit",
        author = "J. R. R. Tolkien",
        date = "1937-09-21"
    )
    private val aLaRechercheDuTempsPerdu = Book(
        title = "À la recherche du temps perdu",
        author = "Marcel Proust",
        date = "1927"
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.bookshelf.addBook(theLordOfTheRings)
        this.bookshelf.addBook(theHobbit)
        this.bookshelf.addBook(aLaRechercheDuTempsPerdu)

        val bookListFragment = BookListFragment.newInstance(bookshelf.getAllBooks())

        supportFragmentManager.beginTransaction()
            .add(R.id.a_main_lyt_container, bookListFragment)
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
        bookshelf.addBook(book)
        this.closeCreateFragment()
    }

    override fun closeCreateFragment() {
        val bookListFragment = BookListFragment.newInstance(bookshelf.getAllBooks())

        supportFragmentManager.beginTransaction()
            .replace(R.id.a_main_lyt_container, bookListFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()

        a_main_btn_creation.visibility = View.VISIBLE
    }
}