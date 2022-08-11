package com.ua.englishschool.users.security.config

import com.ua.englishschool.users.security.jwt.JwtTokenFilter
import com.ua.englishschool.users.security.jwt.JwtTokenProvider
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class JwtConfig(
    private val jwtTokenProvider: JwtTokenProvider
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        val jwtTokenFilter = JwtTokenFilter(jwtTokenProvider)
        builder.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}