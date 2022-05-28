package com.c.mvvmkotlin.RetrofitApi

import com.c.mvvmkotlin.Models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    //baseurl+ "/quotes" + ?page=1
    //https://quotable.io/quotes?page=1

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int): Response<QuoteList>
}