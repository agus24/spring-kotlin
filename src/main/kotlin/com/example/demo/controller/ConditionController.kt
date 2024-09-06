package com.example.demo.controller

import com.example.demo.requests.conditions.ConditionCreateRequest
import com.example.demo.response.BaseResponse
import com.example.demo.response.ConditionResponse
import com.example.demo.services.ConditionService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/conditions")
class ConditionController (
    private val conditionService: ConditionService
) {
    @GetMapping("")
    fun index(@RequestParam(name = "search", required = false) search: String?): BaseResponse<List<ConditionResponse>> {
        return conditionService.getAll(search)
    }

    @PostMapping("")
    fun create(@Valid @RequestBody data: ConditionCreateRequest): BaseResponse<ConditionResponse> {
        return conditionService.createOrUpdate(name = data.name, adjustment = data.adjustment, itemMerkId = data.itemMerkId!!)
    }

    @GetMapping("{id}")
    fun show(@PathVariable id: Long): BaseResponse<ConditionResponse> {
        return conditionService.getOne(id)
    }

    @PutMapping("{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody data: ConditionCreateRequest
    ): BaseResponse<ConditionResponse> {
        return conditionService.createOrUpdate(id, data.name, data.adjustment, data.itemMerkId!!)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): BaseResponse<Nothing?> {
        conditionService.delete(id)

        return BaseResponse(null, message = "Delete Success")
    }
}