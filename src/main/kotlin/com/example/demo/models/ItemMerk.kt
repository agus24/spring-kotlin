package com.example.demo.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

@Entity
class ItemMerk: BaseEntity() {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    lateinit var itemCategory: ItemCategory

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var name: String? = null
}

interface ItemMerkRepository : JpaRepository<ItemMerk, Long>, JpaSpecificationExecutor<ItemMerk>