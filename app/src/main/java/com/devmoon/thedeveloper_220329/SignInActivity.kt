package com.devmoon.thedeveloper_220329

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.devmoon.thedeveloper_220329.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : BaseActivity() {

    lateinit var binding: ActivitySignInBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        auth = Firebase.auth

        setupEvents()
        setValues()
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            moveMainActivity()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // Activity.Result_OK : 정상 완료
        // Activity.Result_CANCEL : 중간에 취소 (실패)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1004) {
            if (resultCode == Activity.RESULT_OK) {
                //결과 Intent(data 매개변수) 에서 구글로그인 결과 꺼내오기
                val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)!!

                //정상적으로 로그인되었다면
                if (result.isSuccess) {
                    //우리의 Firebase 서버에 사용자 이메일정보보내기
                    val account = result.signInAccount
                    firebaseAuthWithGoogle(account)
                }
            }
        }
    }

    override fun setupEvents() {
        binding.btnLogIn2.setOnClickListener {

            loadingDialog.show()
            val inputEmail = binding.edtSignInEmail2.text.toString()
            val inputPassword = binding.edtSignInPassword2.text.toString()

            if (!inputEmail.contains('@')) {
                Toast.makeText(mContext, "올바른 이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else if (inputPassword.isEmpty()) {
                Toast.makeText(mContext, "패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else {
                auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            loadingDialog.dismiss()
                            Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                            moveMainActivity()
                            finish()
                        } else {
                            loadingDialog.dismiss()
                            Toast.makeText(mContext, task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }

        binding.btnSignUp2.setOnClickListener {
            startActivity(Intent(mContext, SignUpActivity::class.java))
        }

        binding.btnSignInGoogle.setOnClickListener {
            // 구글 로그인 관련
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // 필수사항, 사용자의 token을 사용
                .requestEmail() // 사용자의 이메일 사용
                .requestProfile() // 사용자의 프로필 이미지 사용
                .requestId() // 사용자의 아이디 사용
                .build()

            googleSignInClient = GoogleSignIn.getClient(mContext, gso)

            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, 1004)
        }
    }

    override fun setValues() {

    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        //구글로부터 로그인된 사용자의 정보(Credentail)을 얻어온다.
        val credential = GoogleAuthProvider.getCredential(account?.idToken!!, null)

        //그 정보를 사용하여 Firebase의 auth를 실행한다.
        auth.signInWithCredential(credential)
            .addOnCompleteListener {  //통신 완료가 된 후 무슨일을 할지
                    task ->
                if (task.isSuccessful) {
                    // 로그인 처리
                    moveMainActivity()

                } else {
                    // 오류가 난 경우
                    Toast.makeText(this, "다른 로그인 방법을 사용해주세요.", Toast.LENGTH_LONG).show()
                }

            }
    }

    private fun moveMainActivity() {
        Toast.makeText(mContext, "${auth.currentUser!!.email} 접속", Toast.LENGTH_SHORT).show()
        val myIntent = Intent(mContext, MainActivity::class.java)
        startActivity(myIntent)
        finish()
    }
}