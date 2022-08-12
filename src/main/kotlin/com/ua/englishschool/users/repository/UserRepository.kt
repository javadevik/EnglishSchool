package com.ua.englishschool.users.repository

import com.ua.englishschool.users.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
    override fun findAll(): List<UserEntity>
    @Query(value = "UPDATE users SET status = NOT_ACTIVE WHERE id = ?1", nativeQuery = true)
    fun setNotActiveStatus(userId: Long)
}