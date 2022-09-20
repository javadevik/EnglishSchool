package com.ua.englishschool.users.storage

import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    var username: String,
    var password: String,

    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = arrayOf(JoinColumn(name = "user_id")))
    @Enumerated(EnumType.STRING)
    var roles: Set<Role>? = mutableSetOf(Role.STUDENT),

    id: Long? = null,
    createdDate: Long? = null,
    updatedDate: Long? = null,
    status: Status? = Status.ACTIVE,
) : BaseEntity(id, createdDate, updatedDate, status) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntity

        if (username != other.username) return false
        if (password != other.password) return false
        if (roles != other.roles) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + roles.hashCode()
        return result
    }
}