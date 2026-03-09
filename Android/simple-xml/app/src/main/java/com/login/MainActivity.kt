package com.login

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.login.databinding.ActivityMainBinding

const val CHECK_KEY = "CHECK"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var checkTheme: Int = 1

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(CHECK_KEY, checkTheme)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        checkTheme = savedInstanceState.getInt(CHECK_KEY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginButton.setOnClickListener{ checkLogin() }
        binding.hideButton.setOnClickListener{ hideShow() }
        binding.themeButton.setOnClickListener{ changeTheme() }
    }

    private fun changeTheme() {
        checkTheme = if (checkTheme == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            0
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            1
        }

    }

    private fun hideShow() {
        if (binding.password.transformationMethod ==
            HideReturnsTransformationMethod.getInstance()
        ) {
            binding.password.transformationMethod = PasswordTransformationMethod.getInstance()
        } else {
            binding.password.transformationMethod = HideReturnsTransformationMethod.getInstance()
        }
    }

    private fun checkLogin() {
        if (binding.login.text.toString() == "" && binding.password.text.toString() == "") {
            Toast.makeText(
                applicationContext,
                R.string.nullLoginAndPassword,
                Toast.LENGTH_LONG
            ).show()
        } else if (binding.login.text.toString() == "") {
            Toast.makeText(
                applicationContext,
                R.string.nullLogin,
                Toast.LENGTH_LONG
            ).show()
        } else if (binding.password.text.toString() == "") {
            Toast.makeText(
                applicationContext,
                R.string.nullPassword,
                Toast.LENGTH_LONG
            ).show()
        } else {
            Toast.makeText(
                applicationContext,
                R.string.incorrect,
                Toast.LENGTH_LONG
            ).show()
        }
    }

}