package com.ua.englishschool.users.security.jwt

import com.ua.englishschool.users.dto.UserDto
import com.ua.englishschool.users.security.JwtUserDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.security.core.Authentication
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import java.time.Clock
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    @Value("\${security.secret}")
    private var secret: String,
    private val clock: Clock,
    private val jwtUserDetailService: JwtUserDetailService
) {
    @PostConstruct
    protected fun init() {
        secret = Base64.getEncoder().encodeToString(secret.toByteArray())
    }

    fun createToken(userDto: UserDto): String {
        val userId = userDto.id.toString()
        val now = clock.millis()
        val validity = now + 3600000

        return JWT.create()
            .withSubject(userId)
            .withIssuedAt(Date(now))
            .withExpiresAt(Date(validity))
            .sign(Algorithm.HMAC256(secret))
    }

    fun getAuthentication(userId: Long): Authentication? {
        val userDetail = jwtUserDetailService.loadUserByUserId(userId) ?: return null
        return UsernamePasswordAuthenticationToken(userDetail, "", userDetail.authorities)
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        if (bearerToken != null && bearerToken.isNotEmpty() && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7, bearerToken.length)
        }

        return null
    }

    fun tokenVerify(token: String?): DecodedJWT? {
        val jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build()
        return token?.let {
            try {
                jwtVerifier.verify(token)
            }catch (e: Exception) {
                null
            }
        }

    }

}
