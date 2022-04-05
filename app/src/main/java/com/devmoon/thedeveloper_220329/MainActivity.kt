package com.devmoon.thedeveloper_220329

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.devmoon.thedeveloper_220329.databinding.ActivityMainBinding
import com.devmoon.thedeveloper_220329.fragments.CalendarFragment
import com.devmoon.thedeveloper_220329.fragments.GroupFragment
import com.devmoon.thedeveloper_220329.fragments.OverviewFragment
import com.devmoon.thedeveloper_220329.fragments.SettingsFragment
import kotlin.math.hypot
import kotlin.math.max

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    private var isRevealed = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        replaceFragment(OverviewFragment())
        binding.fab.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(resources, R.color.primary_rally_green, null)
        )
        setupEvents()
        setValues()


    }


    override fun setupEvents() {

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.overView -> {
                    replaceFragment(OverviewFragment())
                    actionBarTitle.text = "Overview"
                    showFab()
                }
                R.id.calendar -> {
                    replaceFragment(CalendarFragment())
                    actionBarTitle.text = "Calendar"
                    showFab()
                }
                R.id.group -> {
                    replaceFragment(GroupFragment())
                    actionBarTitle.text = "Group"
                    hideFab()
                }
                R.id.settings -> {
                    replaceFragment(SettingsFragment())
                    actionBarTitle.text = "Settings"
                    hideFab()
                }
            }
            true
        }

        binding.fab.setOnClickListener {
            revealLayoutFun()
        }
    }

    override fun setValues() {
        actionBarTitle.text = "Overview"

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

    private fun hideFab() {
        binding.fab.visibility = View.GONE
    }

    private fun showFab() {
        binding.fab.visibility = View.VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    private fun revealLayoutFun() {
        if (!isRevealed) {
            // get the right and bottom side
            // lengths of the reveal layout
            val x: Int = binding.revealLayout.right
            val y: Int = binding.revealLayout.bottom

            Log.d("reveal", "$x , $y")
            // here the starting radius of the reveal
            // layout is 0 when it is not visible
            val startRadius = 0

            // make the end radius should match
            // the while parent view
            val endRadius = hypot(
                binding.revealLayout.width.toDouble(),
                binding.revealLayout.height.toDouble()
            ).toInt()
            Log.d("reveal", "$endRadius")

            // and set the background tint of the FAB to white
            // color so that it can be visible
            binding.fab.backgroundTintList = ColorStateList.valueOf(
                ResourcesCompat.getColor(
                    resources,
                    R.color.black,
                    null
                )
            )
            // now set the icon as close for the FAB
            binding.fab.setImageResource(R.drawable.ic_close_24)

            // create the instance of the ViewAnimationUtils to
            // initiate the circular reveal animation
            val anim = ViewAnimationUtils.createCircularReveal(
                binding.revealLayout,
                x,
                y,
                startRadius.toFloat(),
                endRadius.toFloat()
            )

            // make the invisible reveal layout to visible
            // so that upon revealing it can be visible to user
            binding.revealLayout.visibility = View.VISIBLE
            // now start the reveal animation
            anim.start()

            // set the boolean value to true as the reveal
            // layout is visible to the user
            isRevealed = true

        } else {
            // get the right and bottom side lengths
            // of the reveal layout
            val x: Int = binding.revealLayout.right
            val y: Int = binding.revealLayout.bottom

            // here the starting radius of the reveal layout is its full width
            val startRadius: Int = max(binding.revealLayout.width, binding.revealLayout.height)

            // and the end radius should be zero
            // at this point because the layout should be closed
            val endRadius = 0

            // now set the background tint of the FAB to green
            // so that it can be visible to the user
            binding.fab.backgroundTintList = ColorStateList.valueOf(
                ResourcesCompat.getColor(
                    resources,
                    R.color.main_Color_black,
                    null
                )
            )

            // now again set the icon of the FAB to plus
            binding.fab.setImageResource(R.drawable.ic_add_24)

            // create the instance of the ViewAnimationUtils to
            // initiate the circular reveal animation
            val anim = ViewAnimationUtils.createCircularReveal(
                binding.revealLayout,
                x,
                y,
                startRadius.toFloat(),
                endRadius.toFloat()
            )

            // now as soon as the animation is ending, the reveal
            // layout should also be closed
            anim.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    binding.revealLayout.visibility = View.GONE
                }

                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })

            // start the closing animation
            anim.start()

            // set the boolean variable to false
            // as the reveal layout is invisible
            isRevealed = false
        }
    }
}