package com.example.meditationapplication.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.meditationapplication.R
import com.example.meditationapplication.login.model.ModelLogin
import com.example.meditationapplication.main.fragment.MainFragment
import com.example.meditationapplication.main.fragment.TopMenuFragment
import com.example.meditationapplication.retrofit.Repository
import com.example.meditationapplication.view_model.MainViewModel
import com.example.meditationapplication.view_model.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var menuFragment: TopMenuFragment
    private lateinit var mainFragment: MainFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel: MainViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[MainViewModel::class.java]

        //get item from intent
        val avatar = intent.getStringExtra("avatar").toString()
        val nickName = intent.getStringExtra("nickName").toString()
        val email = intent.getStringExtra("email").toString()
        val id = intent.getStringExtra("id").toString()
        val token = intent.getStringExtra("token").toString()

        //set item in view model
        viewModel.loginTransit(ModelLogin(id, email, nickName, avatar, token))


        menuFragment = TopMenuFragment(this, viewModel)
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.top_menu, menuFragment)
        fragmentTransaction.commit()

        mainFragment = MainFragment(this, viewModel)
        var fragmentMainTransaction = supportFragmentManager.beginTransaction()
        fragmentMainTransaction.replace(R.id.main_fragment, mainFragment)
        fragmentMainTransaction.commit()

    }


}