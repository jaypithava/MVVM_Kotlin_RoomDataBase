package com.c.mvvmkotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.c.mvvmkotlin.Models.QuoteList


@Database(entities = [QuoteList.Result::class], version = 1)

abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao():QuoteDao

    companion object{
        @Volatile
        private var INSTANCE:QuoteDatabase?=null

        fun getDatabase(context: Context): QuoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }
        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                QuoteDatabase::class.java,
                "quoteDB"
            ).build()
    }

}