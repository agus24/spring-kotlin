package com.example.demo.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Entity
class Conditions: BaseEntity() {

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var name: String = ""

    @Column(columnDefinition = "DECIMAL(20,2) DEFAULT 0", nullable = false)
    var adjustment: BigDecimal = BigDecimal.ZERO

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    lateinit var itemMerk: ItemMerk
}

@Repository
interface ConditionsRepository : JpaRepository<Conditions, Long>, JpaSpecificationExecutor<Conditions>