package com.ismin.android

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.transition.FadeThroughProvider

class CreateBookFragment : Fragment() {
    private var activity: BookCreator? = null;
    private lateinit var edtTitle: EditText
    private lateinit var edtAuthor: EditText
    private lateinit var edtDate: EditText
    private lateinit var card: CardView
    private lateinit var rootLayout: ViewGroup
    private lateinit var blackView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_create_book, container, false)

        rootLayout = rootView.findViewById(R.id.f_create_book_root_layout)
        edtTitle = rootView.findViewById(R.id.f_create_book_edt_title)
        edtAuthor = rootView.findViewById(R.id.f_create_book_edt_author)
        edtDate = rootView.findViewById(R.id.f_create_book_edt_date)
        card = rootView.findViewById(R.id.f_create_book_card)
        blackView = rootView.findViewById(R.id.f_create_book_black_view)

        rootView.setOnClickListener { activity?.closeCreateFragment() }
        rootView.findViewById<Button>(R.id.f_create_book_btn_save).setOnClickListener {
            saveBook()
        }

        ObjectAnimator.ofFloat(card, "translationY", 300f, 0f)
            .apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = 300
                start()
            }

        FadeThroughProvider().createAppear(rootLayout, card)?.start()

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()

        ObjectAnimator.ofFloat(card, "translationY", 0f, 300f)
            .apply {
                interpolator = AccelerateDecelerateInterpolator()
                duration = 100
                start()
            }

        FadeThroughProvider().createDisappear(rootLayout, card)?.start()
    }

    override fun onAttach(context: Context) {
        if (context is BookCreator) {
            activity = context
        }
        super.onAttach(context)
    }

    fun saveBook() {
        activity?.onBookCreated(
            Book(
                edtTitle.text.toString(),
                edtAuthor.text.toString(),
                edtDate.text.toString()
            )
        )
    }
}

interface BookCreator {
    fun onBookCreated(book: Book)
    fun closeCreateFragment()
}