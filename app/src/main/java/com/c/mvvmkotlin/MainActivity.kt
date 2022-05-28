package com.c.mvvmkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.c.mvvmkotlin.Repository.QuoteRepository
import com.c.mvvmkotlin.Repository.Response
import com.c.mvvmkotlin.RetrofitApi.QuoteService
import com.c.mvvmkotlin.RetrofitApi.RetrofitHelper
import com.c.mvvmkotlin.ViewModels.MainViewModel
import com.c.mvvmkotlin.ViewModels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private lateinit var data: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = findViewById(R.id.data)
        val repository = (application as QuoteApplication).quoteRepository

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {
            when (it) {
                is Response.Loading -> {}
                is Response.Success -> {
                    it.data?.let {
                        data.text = "Available Data:" + it.results!!.size.toString()
                        Toast.makeText(
                            this@MainActivity,
                            "Available Data:-" + it.results!!.size.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                is Response.Error -> {
                    Toast.makeText(this@MainActivity, "Something Went Wrong!!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}