package com.example.meditationapplication.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.meditationapplication.R
import com.example.meditationapplication.adapter.RecyclerAdapter
import com.example.meditationapplication.login.model.ModelLogin
import com.example.meditationapplication.retrofit.Repository
import com.example.meditationapplication.view_model.MainViewModel
import com.example.meditationapplication.view_model.MainViewModelFactory
import kotlinx.android.synthetic.main.card_feelings.view.*
import kotlinx.android.synthetic.main.card_meditation.view.*
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment(val context: AppCompatActivity, val viewModel: MainViewModel) : Fragment() {

    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        login()
        inflateFeelings()
        inflateQuotes()
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    private fun login() {
        //get name from view model
        viewModel.loginTransitResponse.observe(context, Observer { response ->
            Log.d("lOGIN", response.nickName)
            val nickName = response.nickName
            textViewHi.text = "С возвращением, $nickName"

        })
    }

    private fun inflateQuotes() {
        val viewModel: MainViewModel =
            ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.quotes()
        viewModel.quotesResponse.observe(context, Observer { response ->
            Log.d("Feelings", response.body()!!.data.toString())

            val adapter = RecyclerAdapter(response!!.body()!!.data, R.layout.card_meditation) {
                description_text.text = it.description
                title_text.text = it.title
                Glide.with(this).load(it.image).centerCrop().into(image_quotes)
            }
            recycler_quotes.adapter = adapter
            recycler_quotes.layoutManager = LinearLayoutManager(context)
        })
    }

    private fun inflateFeelings() {
        val viewModel: MainViewModel =
            ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.feelings()
        viewModel.feelingsResponse.observe(context, Observer { response ->
            Log.d("Feelings", response.body()!!.data.toString())

            val adapter = RecyclerAdapter(response!!.body()!!.data, R.layout.card_feelings) {
                fellingTextView.text = it.title
                Log.d("Feelings", it.title)
                Glide.with(this).load(it.image).centerCrop().into(image_feelings)
            }
            recycler_feelings.adapter = adapter
            val layout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recycler_feelings.layoutManager = layout

        })

    }


}