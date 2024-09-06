package com.example.demo.requests.conditions

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

class ConditionCreateRequest {
    @NotBlank(message = "Name must not be empty")
    lateinit var name: String

    @NotNull(message = "Adjustment must not be empty")
    lateinit var adjustment: BigDecimal

    @NotNull(message = "Adjustment must not be empty")
    var itemMerkId: Long? = null
}
