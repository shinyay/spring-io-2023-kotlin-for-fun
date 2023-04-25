package com.example.demo.controller

import com.example.demo.dto.NewBook
import com.example.demo.dto.SearchBook
import com.example.demo.logger
import com.example.demo.service.BookService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
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

    @PostMapping("/book/delete/{id}")
    fun delete(@PathVariable id: Long): String {
        service.delete(id)
        return "redirect:/"
    }

    @PostMapping("/book/register")
    fun register(@ModelAttribute book: NewBook): String {
        logger.info("Author: ${book.author}")
        logger.info("Name: ${book.name}")
        service.register(book)
        return "redirect:/"
    }
}
