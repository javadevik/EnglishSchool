package com.ua.englishschool.students.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/students")
class StudentController(
        //some injected variables
) {
    @Secured("ROLE_STUDENT")
    @GetMapping("/test/mapping")
    fun testStudentMapping(): ResponseEntity<*> {
        return ResponseEntity("You has access to StudentController", HttpStatus.OK)
    }
}