package com.example.androidbasics.data

import com.example.androidbasics.model.Book
import com.example.androidbasics.network.BooksApiService

interface BooksRepository {
    suspend fun fetchNextPage(query: String): List<Book>
    fun resetPagination()
}

class DefaultBooksRepository(val apiService: BooksApiService) : BooksRepository {

    private var maxResults = 15
    private var currentPage = 0

    override suspend fun fetchNextPage(query: String): List<Book> {
        val startIndex = currentPage * maxResults
        val response =
            apiService.fetchBooks(query = query, maxResults = maxResults, startIndex = startIndex)

        currentPage++
        return response.items
    }

    override fun resetPagination() {
        currentPage = 0
    }
}