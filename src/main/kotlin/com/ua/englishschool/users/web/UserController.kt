package com.ua.englishschool.users.web

import com.ua.englishschool.users.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/signIn")
    fun signIn(): ResponseEntity<*> {
        return ResponseEntity("Welcome to login end-point!", HttpStatus.OK)
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/signUp")
    fun signUp(): ResponseEntity<*> {
        return ResponseEntity("Only for users with admin role", HttpStatus.OK)
    }
}