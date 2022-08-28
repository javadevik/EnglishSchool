package com.ua.englishschool.users.services.impl

import com.ua.englishschool.users.dto.AuthenticationRequestDto
import com.ua.englishschool.users.dto.AuthenticationResponseDto
import com.ua.englishschool.users.dto.RegistrationRequestDto
import com.ua.englishschool.users.dto.RegistrationResponseDto
import com.ua.englishschool.users.security.jwt.JwtTokenProvider
import com.ua.englishschool.users.services.AuthenticationService
import com.ua.englishschool.users.services.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userService: UserService
) : AuthenticationService {
    override fun login(requestDto: AuthenticationRequestDto): AuthenticationResponseDto? {
        val requestUsername = requestDto.username
        val requestPassword = requestDto.password

        authenticationManager
            .authenticate(UsernamePasswordAuthenticationToken(requestUsername, requestPassword))

        val user = userService.findByUsername(requestUsername) ?: return null
        val token = jwtTokenProvider.createToken(user)

        return AuthenticationResponseDto(user.id, requestUsername, token)
    }

    override fun registration(registrationRequest: RegistrationRequestDto): RegistrationResponseDto {
        val userDto = userService.save(registrationRequest)
        return RegistrationResponseDto(userDto.id, userDto.username)
    }
}