package com.devmoon.thedeveloper_220329.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.devmoon.thedeveloper_220329.R
import com.devmoon.thedeveloper_220329.SignInActivity
import com.devmoon.thedeveloper_220329.databinding.FragmentDashboardBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class DashboardFragment : BaseFragment() {

    lateinit var binding: FragmentDashboardBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        binding.btnSubmit.setOnClickListener {
            val inputStr = binding.edtNickname.text
            if (inputStr != null) {
                val uri = "https://ghchart.rshah.org/${inputStr}"
                GlideToVectorYou.init().with(mContext)
                    .requestBuilder.skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.mipmap.ic_launcher)
                    .load(Uri.parse(uri)).into(binding.imgContributes)
                // GlideToVectorYou.justLoadImage(requireActivity(), Uri.parse(uri), binding.imgContributes)

            } else {
                Toast.makeText(mContext, "검색하고 싶은 닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSignOut.setOnClickListener {
            auth.signOut()

            val intent = Intent(mContext, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    override fun setValues() {
        binding.progressbar.progress = 70
        Glide.with(mContext).load(auth.currentUser!!.photoUrl)
            .placeholder(R.mipmap.ic_launcher_foreground).into(binding.imgProfile)
        binding.txtUserEmail.text = auth.currentUser?.email



        GlideToVectorYou.init().with(mContext)
            .requestBuilder.skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .useAnimationPool(true)
            .useUnlimitedSourceGeneratorsPool(true)
            .load(Uri.parse("http://mazassumnida.wtf/api/mini/generate_badge?boj=no1msh"))
            .into(binding.bojImage)

    }

    private fun setActionBar() {

    }
}