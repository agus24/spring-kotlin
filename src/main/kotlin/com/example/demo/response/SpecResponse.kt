package com.example.demo.response

import com.example.demo.models.Spec
import java.sql.Timestamp

class SpecResponse (
    var id: Long,
    var name: String,
    var createdAt: Timestamp?,
    var updatedAt: Timestamp?,
) {

    constructor(spec: Spec): this(
        spec.id,
        spec.name,
        spec.createdAt,
        spec.updatedAt
    )
}