package com.example.androidbasics.data

import com.example.androidbasics.model.Book
import com.example.androidbasics.network.BooksApiService

interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>
}

class DefaultBooksRepository(val apiService: BooksApiService) : BooksRepository {
    override suspend fun getBooks(query: String): List<Book> {
        val response = apiService.fetchBooks(query = query)
        return response.items
    }
}