package com.devmoon.thedeveloper_220329

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext : Context

    abstract fun setupEvents()
    abstract fun setValues()
}