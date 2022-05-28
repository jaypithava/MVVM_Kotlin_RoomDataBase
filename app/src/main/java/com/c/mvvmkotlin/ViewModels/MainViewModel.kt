package com.c.mvvmkotlin.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.c.mvvmkotlin.Models.QuoteList
import com.c.mvvmkotlin.Repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository): ViewModel() {

    init {
        // launching a new coroutine
        GlobalScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    val quotes: LiveData<QuoteList>
        get() = repository.quotes
}