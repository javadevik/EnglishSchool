package com.ua.englishschool.students.dto

import com.ua.englishschool.students.model.Level
import com.ua.englishschool.students.model.StudentEntity
import javax.validation.constraints.Email
import javax.validation.constraints.Pattern

class StudentDto(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    @Pattern(regexp = "^\\+?3?8?(0[\\s\\.-]\\d{2}[\\s\\.-]\\d{3}[\\s\\.-]\\d{2}[\\s\\.-]\\d{2})\$")
    val phone: String,
    @Email(message = "Email is not valid")
    val email: String,
    val level: Level? = Level.FIRST,
) {
    companion object {
        fun toDto(studentEntity: StudentEntity): StudentDto {
            return StudentDto(
                studentEntity.id,
                studentEntity.firstName,
                studentEntity.lastName,
                studentEntity.phone,
                studentEntity.email,
                studentEntity.level
            )
        }
    }
}