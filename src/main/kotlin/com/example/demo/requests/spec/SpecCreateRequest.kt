package com.example.demo.requests.spec

import jakarta.validation.constraints.NotNull

class SpecCreateRequest {
    @NotNull(message = "Name must not be empty")
    lateinit var name: String
}