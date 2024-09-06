package com.example.demo.response

import com.example.demo.models.Gadai
import java.sql.Timestamp
import java.util.*

class GadaiResponse (
    var id: Long,
    var itemType: String?,
    var condition: String?,
    var spec: String?,
    var priceBeforeAdjustment: Int?,
    var price: Int,
    var adjustment: Int,
    var imei: String?,
    var date: Date?,
    var createdAt: Timestamp?,
    var updatedAt: Timestamp?,
) {

    constructor(gadai: Gadai): this(
        gadai.id,
        gadai.itemType?.name,
        gadai.condition?.name,
        gadai.spec?.name,
        gadai.priceBeforeAdjustment,
        gadai.price,
        gadai.priceAdjustment,
        gadai.imei,
        gadai.date,
        gadai.createdAt,
        gadai.updatedAt
    )
}