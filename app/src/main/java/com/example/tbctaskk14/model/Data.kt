package com.example.tbctaskk14.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Data(
    val content: List<Content>
) {
    data class Content(
        val id: String,
        val descriptionKA: String,
        val titleKA: String,
        @Json(name = "publish_date")
        val publishDate: String,
        val cover: String,
    ):Serializable
}

