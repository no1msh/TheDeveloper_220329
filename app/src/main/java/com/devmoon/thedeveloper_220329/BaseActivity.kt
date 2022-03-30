package com.devmoon.thedeveloper_220329

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext : Context

    //nullable한 FirebaseAuth 객체 선언
    var auth: FirebaseAuth? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //auth 객체 초기화
        auth = FirebaseAuth.getInstance()
        mContext = this
    }
    abstract fun setupEvents()
    abstract fun setValues()
}