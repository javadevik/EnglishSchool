package com.ua.englishschool.users.security

import com.ua.englishschool.users.security.jwt.JwtUserFactory
import com.ua.englishschool.users.services.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
abstract class JwtUserDetailService(
    private val userService: UserService
) : UserDetailsService {
    fun loadUserByUserId(userId: Long): UserDetails? {
        return userService.findById(userId)?.let(JwtUserFactory.Companion::createJwtUser)
    }
}