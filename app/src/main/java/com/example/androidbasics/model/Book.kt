package com.example.androidbasics.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//@Serializable
//@OptIn(ExperimentalSerializationApi::class)
//@JsonIgnoreUnknownKeys
//data class BookDetails(val title: String, val authors: List<String>? = null)
//
//@Serializable
//@OptIn(ExperimentalSerializationApi::class)
//@JsonIgnoreUnknownKeys
//data class ImageLinks(@SerialName(value = "thumbnail") val url: String)
//
//@OptIn(ExperimentalSerializationApi::class)
//@JsonIgnoreUnknownKeys
//@Serializable
//data class BooksResponse(val items: List<Book>)
//
//@OptIn(ExperimentalSerializationApi::class)
//@JsonIgnoreUnknownKeys
//@Serializable
//data class Book(
//    val id: String,
//    val description: String? = null,
//    @SerialName(value = "volumeInfo") val bookDetails: BookDetails,
//    @SerialName(value = "imageLinks") val imageLinks: ImageLinks? = null
//)
//

@Serializable
data class BooksResponse(val items: List<Book>)

@Serializable
data class Book(
    val id: String,
    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>? = null,
    val description: String? = null,
    @SerialName("imageLinks")
    val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    @SerialName("thumbnail")
    val url: String
)