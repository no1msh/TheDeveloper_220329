package com.devmoon.thedeveloper_220329.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.devmoon.thedeveloper_220329.R
import com.devmoon.thedeveloper_220329.databinding.FragmentDashboardBinding
import com.devmoon.thedeveloper_220329.utils.ToggleAnimation

class DashboardFragment : BaseFragment() {

    lateinit var binding: FragmentDashboardBinding
    var isTodayExpanded = true // Today 투두의 처음 상태는 expanded
    var isWeeklyExpanded = false // Weekly 투두의 처음 상태는 collapsed

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.imgCollapseToday.setOnClickListener {
            binding.imgCollapseToday.isClickable = false
            val show = toggleLayout(!isTodayExpanded, binding.imgCollapseToday, binding.linearLayTodayTodo)
            isTodayExpanded = show
        }

        binding.imgExpandWeekly.setOnClickListener {
            binding.imgExpandWeekly.isClickable = false
            val show = toggleLayout(!isWeeklyExpanded, binding.imgExpandWeekly,binding.linearLayWeeklyTodo )
            isWeeklyExpanded = show
        }
    }


    override fun setValues() {
    }

    private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: LinearLayout): Boolean {
        ToggleAnimation.toggleArrow(view, isExpanded)
        if (isExpanded) {
            ToggleAnimation.expand(layoutExpand)
        } else {
            ToggleAnimation.collapse(layoutExpand)
        }
        return isExpanded
    }


}