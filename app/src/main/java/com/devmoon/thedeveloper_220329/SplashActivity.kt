package com.devmoon.thedeveloper_220329

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

            // 최근 로그인 했던 내역이 있으면 바로 로그인을 진행시켜서 메인화면으로 이동.
            if (auth?.currentUser != null) {
                Toast.makeText(mContext,"${auth!!.currentUser!!.displayName}님 \n환영합니다.", Toast.LENGTH_LONG).show()
                val mainIntent = Intent(mContext, MainActivity::class.java)
                startActivity(mainIntent)
                finish()

            } else {
                val myIntent = Intent(this, SignInActivity::class.java)
                startActivity(myIntent)
                finish()
            }

        }, 2500)
    }

}