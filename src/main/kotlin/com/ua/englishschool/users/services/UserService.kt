package com.ua.englishschool.users.services

import com.ua.englishschool.users.dto.RegistrationRequestDto
import com.ua.englishschool.users.dto.RegistrationResponseDto
import com.ua.englishschool.users.dto.UserDto

interface UserService {
    fun save(registrationDto: RegistrationRequestDto): UserDto
    fun findById(userId: Long): UserDto?
    fun findByUsername(username: String): UserDto?
    fun findAll(): List<UserDto>
    fun setNotActiveStatus(userId: Long)
    fun isUsernameAlreadyUse(username: String): Boolean
}