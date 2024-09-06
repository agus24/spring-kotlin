package com.example.demo.response

import com.example.demo.models.Conditions
import java.math.BigDecimal
import java.sql.Timestamp

class ConditionResponse(
    var id: Long,
    var name: String,
    var adjustment: BigDecimal,
    var merk: String?,
    var createdAt: Timestamp?,
    var updatedAt: Timestamp?,
) {

    constructor(condition: Conditions) : this(
        condition.id,
        condition.name,
        condition.adjustment,
        condition.itemMerk.name,
        condition.createdAt,
        condition.updatedAt,
    ) {}
}
