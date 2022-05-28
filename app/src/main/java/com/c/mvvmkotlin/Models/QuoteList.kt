package com.c.mvvmkotlin.Models


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
    data class Result(
        @SerializedName("tags")
        val tags: List<String?>?,
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