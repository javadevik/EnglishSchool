package com.ua.englishschool.users.usecases.impl

import com.ua.englishschool.users.dto.AuthenticationResponseDto
import com.ua.englishschool.users.dto.RegistrationRequestDto
import com.ua.englishschool.users.dto.RegistrationResponseDto
import com.ua.englishschool.users.services.UserService
import com.ua.englishschool.users.usecases.UserUseCases
import org.springframework.stereotype.Component

@Component
class UserUseCasesImpl(
        private val userService: UserService
): UserUseCases {
    fun login(authenticationRequest: RegistrationRequestDto): AuthenticationResponseDto? {
        // TODO change return type on UseCaseResult
        if (!isUserExists(authenticationRequest.username)) {
            return null
        }
        return null
    }

    fun registrationUseCase(registrationRequest: RegistrationRequestDto): RegistrationResponseDto? {
        // TODO change return type on UseCaseResult
        if (isUserExists(registrationRequest.username)) {
            return null
        }
        val userSaved = userService.save(registrationRequest)
        return RegistrationResponseDto.toDto(userSaved)
    }

    private fun isUserExists(username: String): Boolean {
        return userService.isUsernameAlreadyUse(username)
    }
}
