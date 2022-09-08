package com.devmoon.thedeveloper_220329

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.devmoon.thedeveloper_220329.adapters.MainViewPagerAdapter
import com.devmoon.thedeveloper_220329.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    // '한번 더 누르면 종료' 관련 변수들
    private var backKeyPressedTime: Long = 0
    private val toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setActionbar()
        setViewPager()
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        actionBarProfileImg.setOnClickListener {
            binding.bottomNav.selectedItemId = R.id.settings
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(mContext, AddTodoListActivity::class.java))
        }
    }

    override fun setValues() {

    }


    private fun setActionbar() {
        actionBarTitle.text = "Dashboard"
        Glide.with(mContext).load(auth.currentUser?.photoUrl).error(R.mipmap.ic_launcher).into(actionBarProfileImg)
    }

    private fun setViewPager() {

        // 페이저에 어댑터 연결
        binding.mainViewPager2.adapter = MainViewPagerAdapter(this)

        binding.mainViewPager2.offscreenPageLimit = 4 // 사용자가 다른 페이지를 봐도 프래그먼트가 종료되지 않음

        binding.mainViewPager2.isUserInputEnabled = false // swipe 액션 삭제

        // 슬라이드 하여 페이지가 변경되면 바텀네비게이션의 탭도 그 페이지로 활성화
        binding.mainViewPager2.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bottomNav.menu.getItem(position).isChecked = true

                }
            }

        )

        // 바텀네비게이션뷰 아이템 클릭 리스너 구현
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.dashBoard -> {
                    binding.mainViewPager2.setCurrentItem(0,false)
                    actionBarTitle.text = "Dashboard"
                    binding.fab.visibility = View.VISIBLE
                    return@setOnItemSelectedListener true
                }
                R.id.calendar -> {
                    binding.mainViewPager2.setCurrentItem(1,false)
                    actionBarTitle.text = "Calendar"
                    binding.fab.visibility = View.VISIBLE
                    return@setOnItemSelectedListener true
                }
                R.id.group -> {
                    binding.mainViewPager2.setCurrentItem(2,false)
                    actionBarTitle.text = "Group"
                    binding.fab.visibility = View.GONE
                    return@setOnItemSelectedListener true
                }
                R.id.settings -> {
                    binding.mainViewPager2.setCurrentItem(3,false)
                    actionBarTitle.text = "Settings"
                    binding.fab.visibility = View.GONE
                    return@setOnItemSelectedListener true
                }

                else -> {
                    return@setOnItemSelectedListener false
                }


            }
        }
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