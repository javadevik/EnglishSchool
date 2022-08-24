package com.ua.englishschool.users.services

import com.ua.englishschool.users.dto.AuthenticationRequestDto
import com.ua.englishschool.users.dto.AuthenticationResponseDto
import com.ua.englishschool.users.dto.RegistrationRequestDto
import com.ua.englishschool.users.dto.RegistrationResponseDto

interface AuthenticationService {
    fun login(requestDto: AuthenticationRequestDto): AuthenticationResponseDto?
    fun registration(registrationRequest: RegistrationRequestDto): RegistrationResponseDto
}