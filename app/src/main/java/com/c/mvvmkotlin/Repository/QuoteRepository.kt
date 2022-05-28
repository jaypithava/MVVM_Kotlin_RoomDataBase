package com.c.mvvmkotlin.Repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.c.mvvmkotlin.Models.QuoteList
import com.c.mvvmkotlin.RetrofitApi.QuoteService
import com.c.mvvmkotlin.db.QuoteDatabase
import com.c.mvvmkotlin.utils.NetworkUtils

class QuoteRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quoteLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quoteLiveData

    suspend fun getQuotes(page: Int) {

        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = quoteService.getQuotes(page)
            if (result?.body() != null) {
                quoteDatabase.quoteDao()
                    .addQuotes(result.body()!!.results as List<QuoteList.Result>)
                quoteLiveData.postValue(result.body())

            }
        } else {
            val quotes = quoteDatabase.quoteDao().getQuotes()
            val quoteList = QuoteList(1, 1, 1, 1, 1, quotes)
            quoteLiveData.postValue(quoteList)

        }
    }
}