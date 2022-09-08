package com.devmoon.thedeveloper_220329.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.devmoon.thedeveloper_220329.R
import com.devmoon.thedeveloper_220329.databinding.FragmentCalendarBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class CalendarFragment : BaseFragment() {

    lateinit var binding: FragmentCalendarBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
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
    }

    override fun setValues() {

    }
}
