package com.ua.englishschool.users.domain

import com.ua.englishschool.common.errors.OperationError
import com.ua.englishschool.users.services.UserService
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ua.englishschool.common.errors.NotFoundError
import com.ua.englishschool.users.security.jwt.JwtTokenProvider
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component

interface UserLoginUseCase {
    fun login(authenticationRequest: AuthenticationRequest): Either<OperationError, AuthenticationResponse>
}

@Component
class UserLoginUseCaseBean(
        private val userService: UserService,
        private val jwtTokenProvider: JwtTokenProvider,
        private val authenticationManager: AuthenticationManager
): UserLoginUseCase {
    override fun login(authenticationRequest: AuthenticationRequest): Either<OperationError, AuthenticationResponse> {
        val requestUsername = authenticationRequest.username
        val requestPassword = authenticationRequest.password

        authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken(requestUsername, requestPassword))

        val user = userService.findByUsername(requestUsername)
                ?: return NotFoundError("Cannot find $requestUsername").left()
        val token = jwtTokenProvider.createToken(user)

        return AuthenticationResponse(user.id, token, requestUsername).right()
    }
}
