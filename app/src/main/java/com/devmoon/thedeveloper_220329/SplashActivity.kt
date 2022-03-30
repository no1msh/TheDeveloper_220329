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

            if (auth?.currentUser != null) {
                Toast.makeText(mContext,"${auth!!.currentUser!!.email}님 \n환영합니다.", Toast.LENGTH_SHORT).show()
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