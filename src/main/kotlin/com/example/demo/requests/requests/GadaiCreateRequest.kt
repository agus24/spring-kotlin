package com.example.demo.requests.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

class GadaiCreateRequest {

    @NotNull(message = "Barang tipe tidak bisa kosong.")
    var itemTypeId: Long? = null

    @NotNull(message = "Kondisi tidak bisa kosong.")
    var conditionId: Long? = null

    @NotBlank(message = "Imei tidak bisa kosong")
    lateinit var imei: String

}