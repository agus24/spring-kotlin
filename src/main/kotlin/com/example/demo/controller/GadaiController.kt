package com.example.demo.controller

import com.example.demo.requests.gadai.GadaiCreateRequest
import com.example.demo.response.BaseResponse
import com.example.demo.response.GadaiResponse
import com.example.demo.services.GadaiService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/gadai")
class GadaiController(
    private val gadaiService: GadaiService
) {

    @GetMapping("")
    fun index(
        @RequestParam(name = "search", required = false) search: String?
    ): BaseResponse<List<GadaiResponse>> {
        return gadaiService.getAll(search)
    }

    @PostMapping("")
    fun create(
        @Valid @RequestBody request: GadaiCreateRequest
    ): BaseResponse<GadaiResponse> {
        return gadaiService.createOrUpdate(null, request.itemTypeId!!, request.conditionId!!, request.imei)
    }
}