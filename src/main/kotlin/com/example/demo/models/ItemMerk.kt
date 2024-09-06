package com.example.demo.models

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

@Entity
class ItemMerk: BaseEntity() {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    lateinit var itemCategory: ItemCategory

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var name: String? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var conditions: MutableList<Conditions>? = null
}

interface ItemMerkRepository : JpaRepository<ItemMerk, Long>, JpaSpecificationExecutor<ItemMerk>