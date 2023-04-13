package com.example.demo.controller

import com.example.demo.service.BookService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BookController(val service: BookService) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("books",service.getBooks())

        return "index"
    }
}
