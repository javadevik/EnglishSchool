package com.ua.englishschool.users.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.ua.englishschool.users.storage.Role
import com.ua.englishschool.users.storage.Status
import com.ua.englishschool.users.storage.UserEntity

class UserDetails(
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
        fun toDto(userEntity: UserEntity): UserDetails {
            return UserDetails(
                userEntity.id!!,
                userEntity.username,
                userEntity.password,
                userEntity.status!!,
                userEntity.roles!!
            )
        }
    }
}