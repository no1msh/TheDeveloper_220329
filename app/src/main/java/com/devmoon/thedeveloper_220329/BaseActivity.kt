package com.devmoon.thedeveloper_220329

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

    // 로딩다이얼로그
    lateinit var loadingDialog: LoadingDialog

    // 액션바의 뷰
    lateinit var actionBarTitle : TextView
    lateinit var actionBarProfileImg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        // 로딩 다이얼로그
        loadingDialog = LoadingDialog(mContext)

        // ActionBar 설정
        supportActionBar?.let {
            // supportActionBar가 null이 아닐때 실행
            setCustomActionbar()
        }
    }

    abstract fun setupEvents()
    abstract fun setValues()

    fun setCustomActionbar() {

        val defaultActionBar = supportActionBar!!
        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        defaultActionBar.setCustomView(R.layout.custom_action_bar)

        val toolbar = defaultActionBar.customView.parent as Toolbar
        toolbar.setContentInsetsAbsolute(0,0)

        // 커스텀 뷰 적용 이후에 actionBar view들에 연결
        actionBarTitle = defaultActionBar.customView.findViewById(R.id.action_bar_title)
        actionBarProfileImg = defaultActionBar.customView.findViewById(R.id.action_bar_profile_img)

    }
}