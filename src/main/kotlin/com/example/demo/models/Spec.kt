package com.example.demo.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Entity
class Spec: BaseEntity() {
    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var name: String = ""
}

@Repository
interface SpecRepository : JpaRepository<Spec, Long>, JpaSpecificationExecutor<Spec>