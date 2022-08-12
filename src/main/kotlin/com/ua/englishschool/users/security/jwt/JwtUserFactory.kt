package com.ua.englishschool.users.security.jwt

import com.ua.englishschool.users.dto.UserDto
import com.ua.englishschool.users.model.Role
import com.ua.englishschool.users.model.Status.ACTIVE
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

class JwtUserFactory {
    companion object {
        fun createJwtUser(userDto: UserDto): JwtUser {
            return JwtUser(
                userDto.id,
                userDto.username,
                userDto.password,
                userDto.status == ACTIVE,
                toSetOfGrantedAuthorities(userDto.roles)
            )
        }

        private fun toSetOfGrantedAuthorities(roles: Set<Role>): Set<GrantedAuthority> {
            return roles.stream()
                .map { role -> SimpleGrantedAuthority("ROLE_${role.name}") }
                .collect(Collectors.toSet())
        }
    }
}