package com.example.demo.controller

import com.example.demo.dto.SearchBook
import com.example.demo.service.BookService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class BookController(val service: BookService) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("books",service.getBooks())

        return "index"
    }

    @PostMapping("/search")
    fun search(@ModelAttribute searchBook: SearchBook, model: Model): String {
        val searchResult = service.search(searchBook)
        model.addAttribute("books", searchResult)
        return "index"
    }
}
