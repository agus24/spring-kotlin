package com.example.demo.response

import com.example.demo.models.ItemMerk
import java.sql.Timestamp

class ItemMerkResponse(
    var id: Long,
    var name: String?,
    var category: String,
    var createdAt: Timestamp?,
    var updatedAt: Timestamp?,
) {

    constructor(itemMerk: ItemMerk): this(
        id = itemMerk.id,
        name = itemMerk.name,
        category = itemMerk.itemCategory.name,
        createdAt = itemMerk.createdAt,
        updatedAt = itemMerk.updatedAt,
    )
}
