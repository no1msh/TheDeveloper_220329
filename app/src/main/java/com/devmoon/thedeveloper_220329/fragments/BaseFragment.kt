package com.devmoon.thedeveloper_220329.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    lateinit var  mContext : Context

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext = requireContext()
    }

    abstract fun setupEvents()
    abstract fun setValues()

}