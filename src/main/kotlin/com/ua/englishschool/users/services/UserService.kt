package com.ua.englishschool.users.services

import com.ua.englishschool.users.dto.RegistrationResponseDto
import com.ua.englishschool.users.dto.UserDto

interface UserService {
    fun save(registrationDto: RegistrationResponseDto): UserDto
    fun findById(userId: Long): UserDto?
    fun findByUsername(username: String): UserDto?
    fun findAll(): List<UserDto>
    fun setNotActiveStatus(userId: Long)
}