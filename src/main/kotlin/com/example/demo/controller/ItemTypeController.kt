package com.example.demo.controller

import com.example.demo.requests.itemType.ItemTypeCreateRequest
import com.example.demo.response.BaseResponse
import com.example.demo.response.ItemTypeResponse
import com.example.demo.services.ItemTypeService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/item-types")
class ItemTypeController (
    private val itemTypeService: ItemTypeService
) {
    @GetMapping("")
    fun index(@RequestParam(name = "search", required = false) search: String?): BaseResponse<List<ItemTypeResponse>> {
        return itemTypeService.getAll(search)
    }

    @PostMapping("")
    fun create(@Valid @RequestBody data: ItemTypeCreateRequest): BaseResponse<ItemTypeResponse> {
        return itemTypeService.createOrUpdate(name = data.name, itemMerkId = data.itemMerkId!!)
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): BaseResponse<ItemTypeResponse> {
        return itemTypeService.getOne(id)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody data: ItemTypeCreateRequest
    ): BaseResponse<ItemTypeResponse> {
        return itemTypeService.createOrUpdate(id, data.name, data.itemMerkId!!)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): BaseResponse<Nothing?> {
        itemTypeService.delete(id)

        return BaseResponse(null, message = "Delete Success")
    }
}