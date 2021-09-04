package com.example.meditationapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.meditationapplication.login.LoginActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
    }

    fun buttonLogin(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}