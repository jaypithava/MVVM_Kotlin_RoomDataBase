package com.c.mvvmkotlin.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.c.mvvmkotlin.Models.QuoteList
import com.c.mvvmkotlin.RetrofitApi.QuoteService
import com.c.mvvmkotlin.db.QuoteDatabase

class QuoteRepository(private val quoteService: QuoteService,private val quoteDatabase: QuoteDatabase) {

    private val quoteLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quoteLiveData

    suspend fun getQuotes(page: Int) {
        val result = quoteService.getQuotes(page)
        if (result?.body() != null) {
            quoteDatabase.quoteDao().addQuotes(result.body()!!.results as List<QuoteList.Result>)
            quoteLiveData.postValue(result.body())
        }
    }
}