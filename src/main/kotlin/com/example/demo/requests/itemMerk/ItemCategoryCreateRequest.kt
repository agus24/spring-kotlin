package com.example.demo.requests.itemMerk

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class ItemCategoryCreateRequest {
    @NotNull(message = "Name must not blank")
    @NotBlank(message = "Name must not blank")
    lateinit var name: String

    @NotNull(message = "itemTypeId must not empty")
    var itemTypeId: Long? = null
}
