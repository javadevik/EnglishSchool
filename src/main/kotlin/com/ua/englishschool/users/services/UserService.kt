package com.ua.englishschool.users.services

import com.ua.englishschool.users.dto.RegistrationUserDto
import com.ua.englishschool.users.dto.UserDto

interface UserService {
    fun save(registrationDto: RegistrationUserDto): UserDto
    fun findById(userId: Long): UserDto?
    fun findByUsername(username: String): UserDto?
    fun findAll(): List<UserDto>
    fun setNotActiveStatus(userId: Long)
}