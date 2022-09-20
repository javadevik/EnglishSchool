package com.ua.englishschool.users.domain

class AuthenticationResponse(
        val userId: Long,
        val token: String,
        val username: String
)