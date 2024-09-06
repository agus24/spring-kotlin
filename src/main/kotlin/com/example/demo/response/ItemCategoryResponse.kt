package com.example.demo.response

import com.example.demo.models.ItemCategory
import java.sql.Timestamp

class ItemCategoryResponse(
    var id: Long? = null,
    var name: String? = null,
    var createdAt: Timestamp? = null,
    var updatedAt: Timestamp? = null,
) {

    constructor(itemCategory: ItemCategory): this(
        itemCategory.id,
        itemCategory.name,
        itemCategory.createdAt,
        itemCategory.updatedAt,
    )
}