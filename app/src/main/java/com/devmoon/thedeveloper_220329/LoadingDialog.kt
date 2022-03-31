package com.devmoon.thedeveloper_220329

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle

class LoadingDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)

        // 취소 및 백버튼 동작 정지
        setCancelable(false)

        // 투명 배경
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}