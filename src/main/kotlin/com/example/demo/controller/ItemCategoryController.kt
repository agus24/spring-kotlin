package com.example.demo.controller

import com.example.demo.requests.ItemCategory.ItemCategoryCreateRequest
import com.example.demo.response.BaseResponse
import com.example.demo.response.ItemCategoryResponse
import com.example.demo.services.ItemCategoryService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/item-categories")
class ItemCategoryController(
    private val itemCategoryService: ItemCategoryService
) {

    @GetMapping("")
    fun index(@RequestParam(name = "search", required = false) search: String?): BaseResponse<List<ItemCategoryResponse>> {
        return itemCategoryService.getAll(search)
    }

    @PostMapping("")
    fun create(@Valid @RequestBody data: ItemCategoryCreateRequest): BaseResponse<ItemCategoryResponse> {
        return itemCategoryService.createOrUpdate(name = data.name!!)
    }

    @GetMapping("{id}")
    fun show(@PathVariable id: Long): BaseResponse<ItemCategoryResponse> {
        return itemCategoryService.getOne(id)
    }

    @PutMapping("{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody data: ItemCategoryCreateRequest
    ): BaseResponse<ItemCategoryResponse> {
        return itemCategoryService.createOrUpdate(id, data.name!!)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): BaseResponse<Nothing?> {
        itemCategoryService.delete(id)

        return BaseResponse(null, message = "Delete Success")
    }
}