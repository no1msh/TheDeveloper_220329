package com.devmoon.thedeveloper_220329

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.devmoon.thedeveloper_220329.databinding.ActivityMainBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.firebase.auth.FirebaseUser

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnSubmit.setOnClickListener {
            val inputStr = binding.edtNickname.text
            if (inputStr != null) {
                val uri = "https://ghchart.rshah.org/${inputStr}"
                GlideToVectorYou.justLoadImage(this, Uri.parse(uri), binding.imgContributes)
            } else {
                Toast.makeText(this, "검색하고 싶은 닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun setValues() {



    }

}