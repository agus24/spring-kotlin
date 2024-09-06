package com.example.demo.controller

import com.example.demo.response.BaseResponse
import com.example.demo.response.ImeiResponse
import com.example.demo.services.ImeiService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/imei")
class ImeiController(
    private val imeiService: ImeiService
) {

    @GetMapping("")
    fun index(@RequestParam(name = "search", required = false) search: String?): BaseResponse<List<ImeiResponse>> {
        return imeiService.getAll(search)
    }

}