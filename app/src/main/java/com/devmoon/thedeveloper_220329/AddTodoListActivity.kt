package com.devmoon.thedeveloper_220329

import android.os.Bundle
import android.view.View

class AddTodoListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo_list)

        setupEvents()
        setValues()
        setActionBar()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }

    private fun setActionBar() {
        actionBarBackImg.visibility = View.VISIBLE
        actionBarTitle.text = "Add Todo List"
        actionBarProfileImg.visibility = View.GONE
    }

    override fun onBackPressed() {
        actionBarBackImg.performClick()
    }

    override fun onDestroy() {
        super.onDestroy()
        actionBarBackImg.visibility = View.GONE
    }
}