package com.c.mvvmkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.c.mvvmkotlin.Repository.QuoteRepository
import com.c.mvvmkotlin.RetrofitApi.QuoteService
import com.c.mvvmkotlin.RetrofitApi.RetrofitHelper
import com.c.mvvmkotlin.ViewModels.MainViewModel
import com.c.mvvmkotlin.ViewModels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository=(application as QuoteApplication).quoteRepository

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            Log.d("jay", "onCreate: "+it.results.toString())
        })
    }
}