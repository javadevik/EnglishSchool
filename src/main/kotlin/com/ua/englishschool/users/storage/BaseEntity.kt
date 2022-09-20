package com.ua.englishschool.users.storage

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @CreatedDate
    @Column(name = "created_at")
    open var createdDate: Long? = null,

    @LastModifiedDate
    @Column(name = "last_modified_at")
    open var updatedDate: Long? = null,

    @Enumerated(EnumType.STRING)
    open var status: Status? = Status.ACTIVE
)