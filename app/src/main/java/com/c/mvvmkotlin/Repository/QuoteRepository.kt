package com.c.mvvmkotlin.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.c.mvvmkotlin.Models.QuoteList
import com.c.mvvmkotlin.RetrofitApi.QuoteService

class QuoteRepository(private val quoteService: QuoteService) {

    private val quoteLiveData = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
        get() = quoteLiveData

    suspend fun getQuotes(page: Int) {
        val result = quoteService.getQuotes(page)
        if (result?.body() != null) {
            quoteLiveData.postValue(result.body())


        }
    }
}