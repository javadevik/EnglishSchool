package com.ua.englishschool.students.repository

import com.ua.englishschool.students.model.Level
import com.ua.englishschool.students.model.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<StudentEntity, Long> {
    fun findByPhone(phone: String): StudentEntity?
    fun findByEmail(email: String): StudentEntity?
    override fun findAll(): List<StudentEntity>
    fun findAllByLevel(level: Level): List<StudentEntity>
}