package com.example.assignment3

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        return view
    }

    private fun setBackground(colorId: Int) {
        Log.d("SettingsFragment", "Setting background color to ${colorId}")
        val color = ContextCompat.getColor(requireContext(), colorId)
        requireActivity().findViewById<View>(android.R.id.content).setBackgroundColor(color)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val radioGroup = view.findViewById<RadioGroup>(R.id.background_color_group)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.red_color -> setBackground(R.color.red)
                R.id.green_color -> setBackground(R.color.green)
                R.id.blue_color -> setBackground(R.color.blue)
                R.id.white_color -> setBackground(R.color.white)
            }

    }
}
}
