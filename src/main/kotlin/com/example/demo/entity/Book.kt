package com.example.demo.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
class Book(var name: String?, var author: String?) {
    @Id
    @GeneratedValue
    var id: Long? = null

    constructor() : this("", "")
}
