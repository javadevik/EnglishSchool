package com.ua.englishschool.common.storage

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @CreatedDate
    @Column(name = "created_at")
    var createdDate: Long? = null,

    @LastModifiedDate
    @Column(name = "last_modified_at")
    var updatedDate: Long? = null
)