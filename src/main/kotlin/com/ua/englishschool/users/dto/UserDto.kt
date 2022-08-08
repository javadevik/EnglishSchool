package com.ua.englishschool.users.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.ua.englishschool.users.model.Role
import com.ua.englishschool.users.model.Status
import com.ua.englishschool.users.model.UserEntity

class UserDto(
    val id: Long,

    val username: String,

    @JsonIgnore
    val password: String,

    @JsonIgnore
    val status: Status,

    @JsonIgnore
    val roles: Set<Role>,
) {
    companion object {
        fun toDto(userEntity: UserEntity): UserDto {
            return UserDto(
                userEntity.id!!,
                userEntity.username,
                userEntity.password,
                userEntity.status!!,
                userEntity.roles!!
            )
        }
    }
}