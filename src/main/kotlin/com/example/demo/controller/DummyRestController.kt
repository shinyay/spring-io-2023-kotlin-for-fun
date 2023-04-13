package com.example.demo.controller

import com.example.demo.entity.Book
import com.example.demo.logger
import com.example.demo.service.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class DummyRestController(val service: BookService) {

    @GetMapping("/books")
    fun validateOperationListData(): MutableList<Book> {
        logger.info("DummyRestController - validateOperationListData")
        return service.getBooks()
    }
}
