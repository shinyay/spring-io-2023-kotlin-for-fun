package com.example.demo.service

import com.example.demo.dto.SearchBook
import com.example.demo.entity.Book
import com.example.demo.logger
import com.example.demo.repository.BookRepository
import jakarta.annotation.PostConstruct
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.*

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
            probe.name = searchBook.value
            probe.author = searchBook.value
        }

        val example: Example<Book> = Example.of(
            probe,
            ExampleMatcher.matchingAny()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        )
        return repository.findAll(example)
//        if (StringUtils.hasText(searchBook.name) && StringUtils.hasText(searchBook.author)) {
//            return repository.findByNameContainsOrAuthorContainsIgnoreCase(
//                searchBook.name, searchBook.author
//            )
//        }
//
//        if (StringUtils.hasText(searchBook.name)) {
//            return repository.findByNameContainsIgnoreCase(
//                searchBook.name
//            )
//        }
//
//        if (StringUtils.hasText(searchBook.author)) {
//            return repository.findByAuthorContainsIgnoreCase(
//                searchBook.author
//            )
//        }
//
//        return Collections.emptyList()
    }
}
