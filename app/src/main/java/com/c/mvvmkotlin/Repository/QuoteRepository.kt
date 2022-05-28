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

    private val quoteLiveData = MutableLiveData<Response<QuoteList>>()

    val quotes: LiveData<Response<QuoteList>>
        get() = quoteLiveData

    suspend fun getQuotes(page: Int) {

        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            try {
                val result = quoteService.getQuotes(page)
                if (result?.body() != null) {
                    quoteDatabase.quoteDao()
                        .addQuotes(result.body()!!.results as List<QuoteList.Result>)
                    quoteLiveData.postValue(Response.Success(result.body()))

                }else{
                    quoteLiveData.postValue(Response.Error("API Error"))
                }
            } catch (e: Exception) {
                quoteLiveData.postValue(Response.Error(e.message.toString()))
            }


        } else {
            try {
                val quotes = quoteDatabase.quoteDao().getQuotes()
                val quoteList = QuoteList(1, 1, 1, 1, 1, quotes)
                quoteLiveData.postValue(Response.Success(quoteList))

            } catch (e: Exception) {
                quoteLiveData.postValue(Response.Error(e.message.toString()))
            }
        }
    }

    suspend fun getQuotesBackground(){
        val randomNumber=(Math.random()*10).toInt()
        val result=quoteService.getQuotes(randomNumber)
        if (result?.body() != null) {
            quoteDatabase.quoteDao()
                .addQuotes(result.body()!!.results as List<QuoteList.Result>)
        }
    }
}