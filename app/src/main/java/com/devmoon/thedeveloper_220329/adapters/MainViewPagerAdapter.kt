package com.devmoon.thedeveloper_220329.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.devmoon.thedeveloper_220329.fragments.CalendarFragment
import com.devmoon.thedeveloper_220329.fragments.DashboardFragment
import com.devmoon.thedeveloper_220329.fragments.GroupFragment
import com.devmoon.thedeveloper_220329.fragments.SettingsFragment

class MainViewPagerAdapter( fragment : FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DashboardFragment()
            1 -> CalendarFragment()
            2 -> GroupFragment()
            else -> SettingsFragment()
        }
    }
}