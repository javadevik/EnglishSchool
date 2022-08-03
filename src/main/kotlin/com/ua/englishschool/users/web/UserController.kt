package com.ua.englishschool.users.web

import com.ua.englishschool.users.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/hello")
    fun hello(): ResponseEntity<*> {
        return ResponseEntity("Hello!", HttpStatus.OK)
    }
}