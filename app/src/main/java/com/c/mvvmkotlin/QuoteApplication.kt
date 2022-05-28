package com.c.mvvmkotlin

import android.app.Application
import com.c.mvvmkotlin.Repository.QuoteRepository
import com.c.mvvmkotlin.RetrofitApi.QuoteService
import com.c.mvvmkotlin.RetrofitApi.RetrofitHelper
import com.c.mvvmkotlin.db.QuoteDatabase

class QuoteApplication : Application() {

    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteService, database)
    }
}