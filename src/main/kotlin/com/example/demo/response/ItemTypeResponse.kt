package com.example.demo.response

import com.example.demo.models.ItemType
import java.sql.Timestamp

class ItemTypeResponse (
    var id: Long,
    var name: String?,
    var merk: String?,
    var createdAt: Timestamp?,
    var updatedAt: Timestamp?,
) {

    constructor(itemType: ItemType): this(
        itemType.id,
        itemType.name,
        itemType.itemMerk.name,
        itemType.createdAt,
        itemType.updatedAt,
    )
}