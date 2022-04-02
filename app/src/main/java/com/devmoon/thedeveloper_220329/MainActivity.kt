package com.devmoon.thedeveloper_220329

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.devmoon.thedeveloper_220329.databinding.ActivityMainBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        auth = Firebase.auth
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

        binding.btnSignOut.setOnClickListener {
            auth.signOut()
//            val myIntent = Intent(mContext, SignInActivity :: class.java)
//            startActivity(myIntent)
//            finish()

//            // 앱을 재시작
//            finishAffinity()
//            val intent = Intent(this, SplashActivity::class.java)
//            startActivity(intent)
//            System.exit(0)

            val intent = Intent(this,SignInActivity :: class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

    }

    override fun setValues() {

        Glide.with(mContext).load(auth.currentUser!!.photoUrl).placeholder(R.mipmap.ic_launcher_foreground).into(binding.imgProfile)
        binding.txtUserEmail.text = auth.currentUser?.email
    }

}