package com.devmoon.thedeveloper_220329

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

            val myIntent = Intent(this, SignInActivity :: class.java)
            startActivity(myIntent)
            finish()

        },2500)
    }
}