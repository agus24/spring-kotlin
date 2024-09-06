package com.example.demo.models


import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.Date

@Entity
class Gadai : BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    var itemType: ItemType? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    var condition: Conditions? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    var spec: Spec? = null

    var priceBeforeAdjustment: Int = 0
    var price: Int = 0

    @Column(columnDefinition = "varchar(100)", nullable = false)
    var imei: String? = null

    @Column(columnDefinition = "date", nullable = false)
    var date: Date? = null

}

@Repository
interface GadaiRepository : JpaRepository<Gadai, Long>, JpaSpecificationExecutor<Gadai>