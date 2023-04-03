// Author: Aaryan Kapoor & Matt Nova
package com.example.assignment3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class HelpFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find and initialize the views
        val searchScreenDesc = view.findViewById<TextView>(R.id.search_screen_desc)
        val searchResultDesc = view.findViewById<TextView>(R.id.search_result_desc)


        // Set the text for each section
        searchScreenDesc.text = getString(R.string.search_screen_desc)
        searchResultDesc.text = getString(R.string.search_result_desc)
    }
}
