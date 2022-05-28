package com.c.mvvmkotlin.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.c.mvvmkotlin.Models.QuoteList

@Dao
interface QuoteDao {

    @Insert
    suspend fun addQuotes(quotes:List<QuoteList.Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes():List<QuoteList.Result>


}