package com.ua.englishschool.students.service

import com.ua.englishschool.students.dto.StudentDto
import com.ua.englishschool.students.model.Level

interface StudentService {
    fun save(studentDto: StudentDto): StudentDto
    fun findById(studentId: Long): StudentDto?
    fun findAll(): List<StudentDto>
    fun findAllByLevel(level: Level): List<StudentDto>
    fun findByPhone(phone: String): StudentDto?
    fun findByEmail(email: String): StudentDto?
}