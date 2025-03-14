package com.kotlinexample.knews.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serial
import java.io.Serializable

@Entity
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
): Serializable