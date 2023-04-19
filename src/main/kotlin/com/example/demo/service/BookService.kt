package com.example.demo.service

import com.example.demo.dto.NewBook
import com.example.demo.dto.SearchBook
import com.example.demo.entity.Book
import com.example.demo.repository.BookRepository
import jakarta.annotation.PostConstruct
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

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

    fun search(searchBook: SearchBook): MutableList<Book> {
        val probe = Book()

        if (StringUtils.hasText(searchBook.value)) {
            probe.apply {
                name = searchBook.value
                author = searchBook.value
            }
        }

        val example: Example<Book> = Example.of(
            probe,
            ExampleMatcher.matchingAny()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        )
        return repository.findAll(example)
    }

    fun delete(id: Long) {
        repository.findById(id)
            .map {
                book: Book ->
                repository.delete(book)
                true
            }
            .orElseThrow {
                RuntimeException(
                    "No book at $id"
                )
            }
    }

    fun register(newBook: NewBook) = repository.saveAndFlush(Book(newBook.name, newBook.author))

    fun update(): Book {
        TODO("Need to implement a process to update")
    }
}
