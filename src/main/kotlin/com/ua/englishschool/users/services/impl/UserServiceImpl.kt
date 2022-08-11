package com.ua.englishschool.users.services.impl

import com.ua.englishschool.users.dto.RegistrationUserDto
import com.ua.englishschool.users.dto.UserDto
import com.ua.englishschool.users.model.UserEntity
import com.ua.englishschool.users.repository.UserRepository
import com.ua.englishschool.users.services.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : UserService {
    override fun save(registrationDto: RegistrationUserDto): UserDto {
        val userEntity = UserEntity(
            registrationDto.username,
            passwordEncoder.encode(registrationDto.password)
        )
        val userSaved = userRepository.save(userEntity)
        return UserDto.toDto(userSaved)
    }

    override fun findById(userId: Long): UserDto? {
        val userFound = userRepository.findByIdOrNull(userId)
            ?: return null
        return UserDto.toDto(userFound)
    }

    override fun findByUsername(username: String): UserDto? {
        val userFound = userRepository.findByUsername(username)
            ?: return null
        return UserDto.toDto(userFound)
    }

    override fun findAll(): List<UserDto> {
        val userEntitiesList = userRepository.findAll()
        return userEntitiesList.stream()
            .map(UserDto.Companion::toDto).collect(Collectors.toList())
    }

    override fun setNotActiveStatus(userId: Long) {
        userRepository.setNotActiveStatus(userId)
    }
}
