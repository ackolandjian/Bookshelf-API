package com.ismin.android

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class InfoFragment : Fragment() {
    private var activity: OnFragmentInteractionListener? = null;
   // var linkTextView: TextView = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_info, container, false)
        var linkTextView = rootView.findViewById<TextView>(R.id.linkedin_hyperlink_anna)
        linkTextView.movementMethod = LinkMovementMethod.getInstance()
        // Inflate the layout for this fragment
        return rootView
    }


    override fun onAttach(context: Context) {
        if (context is OnFragmentInteractionListener) {
            activity = context
        }
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    fun findBookByTitle(){
     //   activity?.onFindByName(e.text.toString())
    }

    interface OnFragmentInteractionListener {
        fun onFindByName(title: String)
        fun closeCreateFragment()
    }

}
