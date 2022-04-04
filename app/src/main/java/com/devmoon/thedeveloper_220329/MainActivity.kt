package com.devmoon.thedeveloper_220329

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.devmoon.thedeveloper_220329.databinding.ActivityMainBinding
import com.devmoon.thedeveloper_220329.fragments.GroupFragment
import com.devmoon.thedeveloper_220329.fragments.CalendarFragment
import com.devmoon.thedeveloper_220329.fragments.OverviewFragment
import com.devmoon.thedeveloper_220329.fragments.SettingsFragment

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupEvents()
        setValues()

        replaceFragment(OverviewFragment())
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.overView -> {
                    replaceFragment(OverviewFragment())
                    actionBarTitle.text = "Overview"
                }
                R.id.calendar -> {
                    replaceFragment(CalendarFragment())
                    actionBarTitle.text = "Calendar"
                }
                R.id.group -> {
                    replaceFragment(GroupFragment())
                    actionBarTitle.text = "Group"
                }
                R.id.settings -> {
                    replaceFragment(SettingsFragment())
                    actionBarTitle.text = "Settings"
                }
            }
            true
        }

    }



    override fun setupEvents() {
    }

    override fun setValues() {
        actionBarTitle.text = "Overview"

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

}