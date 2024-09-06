package com.example.demo.response

import com.example.demo.models.Imei
import java.sql.Timestamp

class ImeiResponse(
    var id: Long,
    var imei: String,
    var itemType: String,
    var spec: String,
    var createdAt: Timestamp?,
    var updatedAt: Timestamp?
) {

    constructor(imei: Imei): this(
        imei.id,
        imei.imei!!,
        imei.itemType.name!!,
        imei.spec.name,
        imei.createdAt,
        imei.updatedAt,
    )
}