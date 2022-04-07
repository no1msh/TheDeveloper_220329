package com.devmoon.thedeveloper_220329

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.devmoon.thedeveloper_220329.databinding.ActivityMainBinding
import com.devmoon.thedeveloper_220329.fragments.CalendarFragment
import com.devmoon.thedeveloper_220329.fragments.DashboardFragment
import com.devmoon.thedeveloper_220329.fragments.GroupFragment
import com.devmoon.thedeveloper_220329.fragments.SettingsFragment

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private var backKeyPressedTime: Long = 0
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private val toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        replaceFragment(DashboardFragment())
        binding.fab.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(resources, R.color.primary_rally_green, null)
        )
        setupEvents()
        setValues()


    }


    override fun setupEvents() {

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.dashBoard -> {
                    replaceFragment(DashboardFragment())
                    actionBarTitle.text = "Dashboard"
                    showFab()
                }
                R.id.calendar -> {
                    replaceFragment(CalendarFragment())
                    actionBarTitle.text = "Calendar"
                    showFab()
                }
                R.id.group -> {
                    replaceFragment(GroupFragment())
                    actionBarTitle.text = "Group"
                    hideFab()
                }
                R.id.settings -> {
                    replaceFragment(SettingsFragment())
                    actionBarTitle.text = "Settings"
                    hideFab()
                }
            }
            true
        }

        actionBarProfileImg.setOnClickListener {

            binding.bottomNav.selectedItemId = R.id.settings
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(mContext, AddTodoListActivity :: class.java))
        }
    }

    override fun setValues() {
        binding.bottomNav.selectedItemId = R.id.dashBoard
        Glide.with(mContext).load(auth.currentUser?.photoUrl).error(R.mipmap.ic_launcher)
            .into(actionBarProfileImg)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

    private fun hideFab() {
        binding.fab.visibility = View.GONE
    }

    private fun showFab() {
        binding.fab.visibility = View.VISIBLE
    }

    // 뒤로가기 키를 눌렀을 때
    override fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            val toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finishAffinity()
            toast?.cancel()
        }
    }
}