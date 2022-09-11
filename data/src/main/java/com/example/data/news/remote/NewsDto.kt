package com.example.data.news.remote

import android.os.Parcelable
import com.example.data.news.repository.News
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

class NewsDto {
    @Parcelize
    data class Request(@SerializedName("id") val listID: Int) : Parcelable

    @Parcelize
    data class Response(@SerializedName("listID") val listID: @RawValue List<News>) : Parcelable
}

internal fun NewsDto.Response.toDomain(): List<News> {
    return this.listID
}