package com.c.mvvmkotlin.Models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class QuoteList(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("totalCount")
    val totalCount: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("totalPages")
    val totalPages: Int?,
    @SerializedName("lastItemIndex")
    val lastItemIndex: Int?,
    @SerializedName("results")
    val results: List<Result?>?
) {

    @Entity(tableName = "quote")

    data class Result(
        @PrimaryKey(autoGenerate = true)
        val quoteId: Int,
        @SerializedName("_id")
        val id: String?,
        @SerializedName("author")
        val author: String?,
        @SerializedName("content")
        val content: String?,
        @SerializedName("authorSlug")
        val authorSlug: String?,
        @SerializedName("length")
        val length: Int?,
        @SerializedName("dateAdded")
        val dateAdded: String?,
        @SerializedName("dateModified")
        val dateModified: String?
    )
}