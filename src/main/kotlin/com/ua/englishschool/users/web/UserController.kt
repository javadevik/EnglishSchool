package com.ua.englishschool.users.web

import arrow.core.Either
import com.ua.englishschool.common.dto.ResponseDto
import com.ua.englishschool.common.dto.SuccessResponseDto
import com.ua.englishschool.common.mapErrors
import com.ua.englishschool.users.domain.*
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/")
class UserController(
    private val userLoginUseCase: UserLoginUseCase,
    private val userRegistrationUseCase: UserRegistrationUseCase
) {
    @GetMapping("/sign-in")
    fun signIn(
            @RequestBody authenticationRequest: AuthenticationRequest
    ): ResponseEntity<ResponseDto<AuthenticationResponse?>> {
        return when(val result = userLoginUseCase.login(authenticationRequest)) {
            is Either.Left -> mapErrors(result.value)
            is Either.Right -> ResponseEntity.ok(SuccessResponseDto(result.value))
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/sign-up")
    fun signUp(
            @RequestBody registrationRequest: RegistrationRequest
    ): ResponseEntity<ResponseDto<UserDetails>> {
        return when (val result = userRegistrationUseCase.registration(registrationRequest)) {
            is Either.Left -> mapErrors(result.value)
            is Either.Right -> ResponseEntity.ok(SuccessResponseDto(result.value))
        }
    }
}