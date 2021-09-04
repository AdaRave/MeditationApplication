package com.example.meditationapplication.main.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.meditationapplication.R
import com.example.meditationapplication.view_model.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_top_menu.*


class TopMenuFragment(val context: AppCompatActivity,  val viewModel: MainViewModel) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loadAvatar()
        return inflater.inflate(R.layout.fragment_top_menu, container, false)
    }

    private fun loadAvatar() {
        //get avatar from view model
        viewModel.loginTransitResponse.observe(context, Observer { response ->
            val avatar = response.avatar
            Log.d("LOGIN", avatar)
            Glide.with(this).load(avatar).circleCrop().into(image_view_avatar)
        })
    }


}