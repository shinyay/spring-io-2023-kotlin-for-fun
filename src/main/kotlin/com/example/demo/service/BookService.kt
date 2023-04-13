package com.example.demo.service

import com.example.demo.entity.Book
import com.example.demo.repository.BookRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class BookService(val repository: BookRepository) {

    @PostConstruct
    fun initSampleData() {
        repository.save(
            Book(
                "Learning Spring Boot 3.0",
                "Greg L. Turnquist"
            )
        )
        repository.save(
            Book(
                "Spring in Action",
                "Craig Walls")
        )
        repository.save(
            Book(
                "Spring Boot: Up and Running",
                "Mark Heckler"
            )
        )
    }

    fun getBooks(): MutableList<Book> = repository.findAll()
}
