package com.example.demo.controller

import com.example.demo.entity.Book
import com.example.demo.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DummyRestController(val service: BookService) {

    @GetMapping("/")
    fun validateOperationListData(): MutableList<Book> = service.getBooks()
}
