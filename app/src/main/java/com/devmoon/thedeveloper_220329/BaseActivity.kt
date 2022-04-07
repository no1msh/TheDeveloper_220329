package com.devmoon.thedeveloper_220329

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

    // 로딩다이얼로그
    lateinit var loadingDialog: LoadingDialog

    // 액션바의 뷰
    lateinit var actionBarTitle : TextView
    lateinit var actionBarProfileImg : ImageView
    lateinit var actionBarBackImg : ImageView

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this
        auth = Firebase.auth

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



    private fun setCustomActionbar() {

        val defaultActionBar = supportActionBar!!
        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        defaultActionBar.setCustomView(R.layout.custom_action_bar)

        val toolbar = defaultActionBar.customView.parent as Toolbar
        toolbar.setContentInsetsAbsolute(0,0)

        // 커스텀 뷰 적용 이후에 actionBar view들에 연결
        actionBarTitle = defaultActionBar.customView.findViewById(R.id.action_bar_title)
        actionBarProfileImg = defaultActionBar.customView.findViewById(R.id.action_bar_profile_img)
        actionBarBackImg = defaultActionBar.customView.findViewById(R.id.action_bar_back_img)

        actionBarBackImg.setOnClickListener {
            val ad = AlertDialog.Builder(mContext)
                .setTitle("진행중인 화면을 종료할까요?")
                .setMessage("\n이대로 종료하시면 기입 중이던 내용은 \n전부 사라집니다. 그래도 종료하시겠어요?")
                .setPositiveButton("종료", DialogInterface.OnClickListener { dialog, which ->
                    finish()
                })
                .setNegativeButton("취소", null)
                .show()
        }

    }
}