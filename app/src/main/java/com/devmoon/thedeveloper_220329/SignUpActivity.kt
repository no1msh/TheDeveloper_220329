package com.devmoon.thedeveloper_220329

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.devmoon.thedeveloper_220329.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {

    lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.btnSignUpBack.setOnClickListener {

            if (binding.edtSignUpEmail.text.toString().isEmpty()
                && binding.edtSignUpPassword.text.toString().isEmpty()
                && binding.edtSignUpRepeatPassword.text.toString().isEmpty()) {
                finish()
            }

            else { // 하나라도 작성하다만 상태라면

                val ad = AlertDialog.Builder(mContext)
                    .setTitle("1분이면 됩니다.")
                    .setMessage("\n이대로 종료하시면 기입 중이던 내용은 \n전부 사라집니다. 그래도 종료하시겠어요?")
                    .setPositiveButton("종료", DialogInterface.OnClickListener { dialog, which ->
                        finish()
                    })
                    .setNegativeButton("취소" , null)
                    .show()
            }
        }

        binding.btnSignUpRegister.setOnClickListener {

            // 이메일 항목에 '@'가 포함되어있는지 판단 + 비밀번호와 비밀번호가 일치하는지 여부를 판단

            val signUpEmail = binding.edtSignUpEmail.text.toString()
            val signUpPassword = binding.edtSignUpPassword.text.toString()
            val signUpRepeatPassword = binding.edtSignUpRepeatPassword.text.toString()

            if (!signUpEmail.contains('@')){
                Toast.makeText(mContext, "올바른 이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else if(signUpPassword != signUpRepeatPassword || signUpEmail.isEmpty()) {
                Toast.makeText(mContext, "패스워드를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else { // 모든 검사가 완료된 값으로 회원가입을 진행

                loadingDialog.show() // 시간이 걸리는 작업이기에 로딩창 실행

                auth?.createUserWithEmailAndPassword(signUpEmail,signUpPassword)?.addOnCompleteListener {
                    it ->

                    if (it.isSuccessful) {
                        loadingDialog.dismiss() // 작업이 완료되면 로딩창 종료

                        // 정상적으로 이메일과 비번이 전달되어
                        // 새 유저 계정을 생성과 서버 db 저장 완료 및 로그인
                        // 즉, 기존에 있는 계정이 아니다.
                        Toast.makeText(mContext, "회원 가입이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()

                    } else if (!it.exception?.message.isNullOrEmpty()) {
                        loadingDialog.dismiss()

                        // 예외메시지가 있다면 출력
                        // 에러가 났다거나 서버가 연결이 실패했다거나
                        Toast.makeText(mContext, it.exception?.message , Toast.LENGTH_LONG).show()

                    } else {
                        loadingDialog.dismiss()
                        // 이미 Firebase에 해당이메일과 패스워드가 있는 경우
                        Toast.makeText(mContext, "이미 계정이 존재합니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }
    }

    override fun setValues() {

    }
}