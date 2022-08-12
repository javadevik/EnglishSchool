package com.ua.englishschool.students.service.impl

import com.ua.englishschool.students.dto.StudentDto
import com.ua.englishschool.students.model.Level
import com.ua.englishschool.students.model.StudentEntity
import com.ua.englishschool.students.repository.StudentRepository
import com.ua.englishschool.students.service.StudentService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository
) : StudentService {
    override fun save(studentDto: StudentDto): StudentDto {
        val studentEntity = StudentEntity(
            studentDto.firstName, studentDto.lastName,
            studentDto.phone, studentDto.email,
            studentDto.level
        )
        val studentSaved = studentRepository.save(studentEntity)
        return StudentDto.toDto(studentSaved)
    }

    override fun findById(studentId: Long): StudentDto? {
        val studentFound = studentRepository.findByIdOrNull(studentId)
            ?: return null
        return StudentDto.toDto(studentFound)
    }

    override fun findByPhone(phone: String): StudentDto? {
        val studentFound = studentRepository.findByPhone(phone)
            ?: return null
        return StudentDto.toDto(studentFound)
    }

    override fun findByEmail(email: String): StudentDto? {
        val studentFound = studentRepository.findByEmail(email)
            ?: return null
        return StudentDto.toDto(studentFound)
    }

    override fun findAll(): List<StudentDto> {
        val students = studentRepository.findAll();
        return toListOfDto(students)
    }

    override fun findAllByLevel(level: Level): List<StudentDto> {
        val students = studentRepository.findAllByLevel(level)
        return toListOfDto(students)
    }

    private fun toListOfDto(students: List<StudentEntity>): List<StudentDto> {
        return students.stream()
            .map(StudentDto.Companion::toDto)
            .collect(Collectors.toList())
    }
}