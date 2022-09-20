package com.ua.englishschool.users.security.jwt

import com.ua.englishschool.users.domain.UserDetails
import com.ua.englishschool.users.storage.Role
import com.ua.englishschool.users.storage.Status.ACTIVE
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

class JwtUserFactory {
    companion object {
        fun createJwtUser(userDetails: UserDetails): JwtUser {
            return JwtUser(
                userDetails.id,
                userDetails.username,
                userDetails.password,
                userDetails.status == ACTIVE,
                toSetOfGrantedAuthorities(userDetails.roles)
            )
        }

        private fun toSetOfGrantedAuthorities(roles: Set<Role>): Set<GrantedAuthority> {
            return roles.stream()
                .map { role -> SimpleGrantedAuthority("ROLE_${role.name}") }
                .collect(Collectors.toSet())
        }
    }
}