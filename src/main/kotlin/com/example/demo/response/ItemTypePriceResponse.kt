package com.example.demo.response

import com.example.demo.models.ItemTypePrice
import java.sql.Timestamp

class ItemTypePriceResponse (
    var id: Long,
    var itemType: String,
    var price: Int,
    var spec: String,
    var createdAt: Timestamp?,
    var updatedAt: Timestamp?,
) {
    constructor(itemTypePrice: ItemTypePrice): this(
        itemTypePrice.id,
        itemTypePrice.itemType.name!!,
        itemTypePrice.price,
        itemTypePrice.spec.name,
        itemTypePrice.createdAt,
        itemTypePrice.updatedAt,
    )
}