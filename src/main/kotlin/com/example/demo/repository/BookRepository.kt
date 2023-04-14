package com.example.demo.repository

import com.example.demo.entity.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {
    fun findByNameContainsOrAuthorContainsAllIgnoreCase(
        name: String,
        author: String
    ): MutableList<Book>
}
