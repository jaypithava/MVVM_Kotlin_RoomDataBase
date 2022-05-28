package com.c.mvvmkotlin.WorkManager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.c.mvvmkotlin.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context,parameters: WorkerParameters):Worker(context,parameters) {
    override fun doWork(): Result {
        Log.d("jay", "WorkManager Called: ")
        val repository=(context as QuoteApplication).quoteRepository

        CoroutineScope(Dispatchers.IO).launch{
            repository.getQuotesBackground()
        }
        return Result.success()
    }
}