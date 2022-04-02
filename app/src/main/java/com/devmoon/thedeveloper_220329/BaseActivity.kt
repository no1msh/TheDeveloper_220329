package com.devmoon.thedeveloper_220329

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: Context

    // 로딩다이얼로그
    lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this

        // 로딩 다이얼로그
        loadingDialog = LoadingDialog(mContext)
    }

    abstract fun setupEvents()
    abstract fun setValues()
}