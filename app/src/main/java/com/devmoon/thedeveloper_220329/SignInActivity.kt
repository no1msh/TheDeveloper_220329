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

class SignInActivity : BaseActivity() {

    lateinit var binding: ActivitySignInBinding

    private lateinit var googleSignInClient: GoogleSignInClient

    //nullable한 FirebaseAuth 객체 선언
    var auth: FirebaseAuth? = null;

    override fun onStart() {
        super.onStart()
        if (auth?.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        setupEvents()
        setValues()
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

        binding.btnSignUp.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
            // 회원가입이 완료된 후 finish()를 하면 다시 로그인창으로 올 수 있게 finish()를 적지 않음.
        }

        binding.btnSignInGoogle.setOnClickListener {

            val signInIntent = googleSignInClient?.signInIntent
            startActivityForResult(signInIntent, 1004)
        }


    }


    override fun setValues() {

        //auth 객체 초기화
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // 필수사항, 사용자의 token을 사용
            .requestEmail() // 사용자의 이메일 사용
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }


    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {

        //구글로부터 로그인된 사용자의 정보(Credentail)을 얻어온다.
        val credential = GoogleAuthProvider.getCredential(account?.idToken!!, null)

        //그 정보를 사용하여 Firebase의 auth를 실행한다.
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener {  //통신 완료가 된 후 무슨일을 할지
                    task ->
                if (task.isSuccessful) {
                    // 로그인 처리
                    val myIntent = Intent(mContext, MainActivity::class.java)
                    startActivity(myIntent)
                    finish()

                } else {
                    // 오류가 난 경우!
                    Toast.makeText(this, "다른 로그인 방법을 사용해주세요.", Toast.LENGTH_LONG).show()
                }

            }
    }
}
