package com.questdev.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.questdev.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val myName = MyName("Marquis de Lafayette")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName

        binding.doneButton.setOnClickListener { addNickname(it) }
        binding.nicknameText.setOnClickListener { updateNickname() }
    }

    private fun updateNickname() {
        binding.apply {
            doneButton.visibility = View.VISIBLE
            nicknameEdit.visibility = View.VISIBLE
            nicknameText.visibility = View.GONE
            nicknameEdit.requestFocus()
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.nicknameEdit,0)
    }

    private fun addNickname(view: View) {
        binding.apply {
            if (nicknameEdit.text.isNotEmpty()) {
                nicknameText.visibility = View.VISIBLE
                myName?.nickname = nicknameEdit.text.toString()
                invalidateAll()
                doneButton.visibility = View.GONE
                nicknameEdit.visibility = View.GONE
            } else if (nicknameText.text.isNotEmpty()) {
                nicknameText.visibility = View.VISIBLE
                doneButton.visibility = View.GONE
                nicknameEdit.visibility = View.GONE
            }
        }
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }
}