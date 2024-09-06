package com.example.demo.requests.itemType

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class ItemTypeCreateRequest {
    @NotNull(message = "Name must not blank")
    @NotBlank(message = "Name must not blank")
    lateinit var name: String

    @NotNull(message = "itemMerkId must not empty")
    val itemMerkId: Long? = null
}