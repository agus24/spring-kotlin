package com.example.demo.models


import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Entity
class Imei : BaseEntity() {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    lateinit var itemType: ItemType

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    lateinit var spec: Spec

    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    var imei: String? = null
}

@Repository
interface ImeiRepository : JpaRepository<Imei, Long>, JpaSpecificationExecutor<Imei>