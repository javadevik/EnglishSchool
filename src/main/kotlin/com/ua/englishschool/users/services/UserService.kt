package com.ua.englishschool.users.services

import com.ua.englishschool.users.domain.RegistrationRequest
import com.ua.englishschool.users.domain.UserDetails

interface UserService {
    fun save(registrationRequest: RegistrationRequest): UserDetails
    fun findById(userId: Long): UserDetails?
    fun findByUsername(username: String): UserDetails?
    fun findAll(): List<UserDetails>
    fun setNotActiveStatus(userId: Long)
    fun isUsernameAlreadyUse(username: String): Boolean
}