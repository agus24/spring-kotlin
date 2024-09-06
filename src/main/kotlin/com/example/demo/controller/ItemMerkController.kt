package com.example.demo.controller

import com.example.demo.requests.itemMerk.ItemCategoryCreateRequest
import com.example.demo.response.BaseResponse
import com.example.demo.response.ItemMerkResponse
import com.example.demo.services.ItemMerkService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/item-merks")
class ItemMerkController(
    private val itemMerkService: ItemMerkService
) {

    @GetMapping("")
    fun index(@RequestParam(name = "search", required = false) search: String?): BaseResponse<List<ItemMerkResponse>> {
        return itemMerkService.getAll(search)
    }

    @PostMapping("")
    fun create(@Valid @RequestBody data: ItemCategoryCreateRequest): BaseResponse<ItemMerkResponse> {
        return itemMerkService.createOrUpdate(name = data.name!!, itemTypeId = data.itemTypeId!!)
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): BaseResponse<ItemMerkResponse> {
        return itemMerkService.getOne(id)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody data: ItemCategoryCreateRequest
    ): BaseResponse<ItemMerkResponse> {
        return itemMerkService.createOrUpdate(id, data.name!!, data.itemTypeId!!)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): BaseResponse<Nothing?> {
        itemMerkService.delete(id)

        return BaseResponse(null, message = "Delete Success")
    }
}