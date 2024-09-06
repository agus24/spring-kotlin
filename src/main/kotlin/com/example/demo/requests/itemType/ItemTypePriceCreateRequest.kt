package com.example.demo.requests.itemType

import jakarta.validation.constraints.NotNull

class ItemTypePriceCreateRequest {

    @NotNull(message = "Price must not be empty")
    var price: Int? = null

    @NotNull(message = "Item Type must not be empty")
    var itemTypeId: Long? = null

    @NotNull(message = "Spec must not be empty")
    var specId: Long? = null
}