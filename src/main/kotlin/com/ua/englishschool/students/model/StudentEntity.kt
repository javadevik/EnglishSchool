package com.ua.englishschool.students.model

import com.ua.englishschool.users.model.BaseEntity
import com.ua.englishschool.users.model.Status
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "students")
class StudentEntity(
    var firstName: String,
    var lastName: String,
    var phone: String,
    var email: String,
    var level: Level? = Level.FIRST,

    id: Long? = null,
    createdDate: Long? = null,
    updatedDate: Long? = null,
    status: Status? = Status.ACTIVE
): BaseEntity(id, createdDate, updatedDate, status) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StudentEntity

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (phone != other.phone) return false
        if (email != other.email) return false
        if (level != other.level) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + (level?.hashCode() ?: 0)
        return result
    }
}