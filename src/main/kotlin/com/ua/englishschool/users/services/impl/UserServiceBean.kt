package com.ua.englishschool.users.services.impl

import com.ua.englishschool.users.domain.RegistrationRequest
import com.ua.englishschool.users.domain.UserDetails
import com.ua.englishschool.users.storage.UserEntity
import com.ua.englishschool.users.storage.UserRepository
import com.ua.englishschool.users.services.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserServiceBean(
        private val userRepository: UserRepository,
        private val passwordEncoder: BCryptPasswordEncoder
) : UserService {
    override fun save(registrationRequest: RegistrationRequest): UserDetails {
        val userEntity = UserEntity(
                registrationRequest.username,
            passwordEncoder.encode(registrationRequest.password)
        )
        val userSaved = userRepository.save(userEntity)
        return UserDetails.toDto(userSaved)
    }

    override fun findById(userId: Long): UserDetails? {
        val userFound = userRepository.findByIdOrNull(userId)
            ?: return null
        return UserDetails.toDto(userFound)
    }

    override fun findByUsername(username: String): UserDetails? {
        val userFound = userRepository.findByUsername(username)
            ?: return null
        return UserDetails.toDto(userFound)
    }

    override fun findAll(): List<UserDetails> {
        val userEntitiesList = userRepository.findAll()
        return userEntitiesList.stream()
            .map(UserDetails.Companion::toDto).collect(Collectors.toList())
    }

    override fun isUsernameAlreadyUse(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    override fun setNotActiveStatus(userId: Long) {
        userRepository.setNotActiveStatus(userId)
    }
}
