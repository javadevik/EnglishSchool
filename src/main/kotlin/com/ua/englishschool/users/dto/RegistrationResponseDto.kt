package com.ua.englishschool.users.dto

class RegistrationResponseDto(
    val id: Long,
    val username: String
) {
    companion object {
        fun toDto(userDto: UserDto): RegistrationResponseDto {
            return RegistrationResponseDto(userDto.id, userDto.username)
        }
    }
}