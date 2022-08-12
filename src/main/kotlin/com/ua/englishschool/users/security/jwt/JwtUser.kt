package com.ua.englishschool.users.security.jwt

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtUser(
    private val id: Long,
    private val username: String,
    private val password: String,
    private val enabled: Boolean,
    private val roles: Set<GrantedAuthority>?
) : UserDetails {
    override fun getAuthorities(): Set<GrantedAuthority>? {
        return roles
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return enabled
    }
}