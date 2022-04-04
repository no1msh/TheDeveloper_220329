package com.devmoon.thedeveloper_220329.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.devmoon.thedeveloper_220329.R
import com.devmoon.thedeveloper_220329.databinding.FragmentGroupBinding
import com.google.firebase.auth.FirebaseAuth

class GroupFragment : BaseFragment() {

    lateinit var binding : FragmentGroupBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        }

    override fun setValues() {

    }
}
