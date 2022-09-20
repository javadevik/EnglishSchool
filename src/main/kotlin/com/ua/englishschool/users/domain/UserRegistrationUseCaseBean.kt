package com.ua.englishschool.users.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.ua.englishschool.common.errors.AlreadyExistsError
import com.ua.englishschool.common.errors.OperationError
import com.ua.englishschool.users.services.UserService
import org.springframework.stereotype.Component

interface UserRegistrationUseCase {
    fun registration(registrationRequest: RegistrationRequest): Either<OperationError, UserDetails>
}

@Component
class UserRegistrationUseCaseBean(
        private val userService: UserService
) : UserRegistrationUseCase {
    override fun registration(registrationRequest: RegistrationRequest): Either<OperationError, UserDetails> {
        val username = registrationRequest.username
        if (userService.isUsernameAlreadyUse(username)) {
            return AlreadyExistsError("User $username already exists").left()
        }

        return userService.save(registrationRequest).right()
    }
}