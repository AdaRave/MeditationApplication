package com.example.meditationapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.meditationapplication.R
import com.example.meditationapplication.login.model.ModelLogin
import com.example.meditationapplication.login.model.ModelLoginPost
import com.example.meditationapplication.main.MainActivity
import com.example.meditationapplication.retrofit.Repository
import com.example.meditationapplication.view_model.MainViewModel
import com.example.meditationapplication.view_model.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.NullPointerException

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
    }

    fun login(view: View) {
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel: MainViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[MainViewModel::class.java]

        val email = email_text.text.toString()
        val password = password_text.text.toString()
        viewModel.login(ModelLoginPost(email, password))

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "the field mast not be empty", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.loginResponse.observe(this, Observer { response ->
                Log.d("LOGIN", response.message().toString())

                try {
                    Log.d("LOGIN", "IntentOk")
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("avatar", response.body()!!.avatar)
                    intent.putExtra("nickName", response.body()!!.nickName)
                    intent.putExtra("email", response.body()!!.email)
                    intent.putExtra("id", response.body()!!.id)
                    intent.putExtra("token", response.body()!!.token)
                    startActivity(intent)
                }catch (message : NullPointerException){
                    Log.d("LOGIN", "IntentError")
                    //Toast.makeText(this, "account not found", Toast.LENGTH_SHORT).show()
                }

            })
        }
        return
    }
}