package com.example.demo.controller

import com.example.demo.requests.spec.SpecCreateRequest
import com.example.demo.response.BaseResponse
import com.example.demo.response.SpecResponse
import com.example.demo.services.SpecService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/spec")
class SpecController (
    private val specService: SpecService
) {
    @GetMapping("")
    fun index(@RequestParam(name = "search", required = false) search: String?): BaseResponse<List<SpecResponse>> {
        return specService.getAll(search)
    }

    @PostMapping("")
    fun create(@Valid @RequestBody data: SpecCreateRequest): BaseResponse<SpecResponse> {
        return specService.createOrUpdate(name = data.name)
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): BaseResponse<SpecResponse> {
        return specService.getOne(id)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody data: SpecCreateRequest
    ): BaseResponse<SpecResponse> {
        return specService.createOrUpdate(id, data.name)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): BaseResponse<Nothing?> {
        specService.delete(id)

        return BaseResponse(null, message = "Delete Success")
    }
}