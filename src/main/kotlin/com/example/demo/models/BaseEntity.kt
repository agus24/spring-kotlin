package com.example.demo.models

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.sql.Timestamp

@MappedSuperclass
@DynamicUpdate
open class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    var id: Long = 0

    var createdAt: Timestamp? = null
    var updatedAt: Timestamp? = null

    @PrePersist
    fun prePersist() {

        if(createdAt == null) {
            createdAt = Timestamp(System.currentTimeMillis())
        }

        if (updatedAt == null) {
            updatedAt = Timestamp(System.currentTimeMillis())
        }
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = Timestamp(System.currentTimeMillis())
    }
}