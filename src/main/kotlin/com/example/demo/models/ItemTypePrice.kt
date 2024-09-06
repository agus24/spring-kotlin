package com.example.demo.models


import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Entity
class ItemTypePrice : BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    lateinit var itemType: ItemType

    @Column(columnDefinition = "INT UNSIGNED", nullable = false)
    var price: Int = 0

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    lateinit var spec: Spec
}

@Repository
interface ItemTypePriceRepository : JpaRepository<ItemTypePrice, Long>, JpaSpecificationExecutor<ItemTypePrice>