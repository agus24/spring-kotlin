package com.example.demo.controller

import com.example.demo.requests.itemType.ItemTypePriceCreateRequest
import com.example.demo.response.BaseResponse
import com.example.demo.response.ItemTypePriceResponse
import com.example.demo.services.ItemTypePriceService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/item-type-prices")
class ItemTypePriceController (
    private val itemTypePriceService: ItemTypePriceService
) {
    @GetMapping("")
    fun index(@RequestParam(name = "search", required = false) search: String?): BaseResponse<List<ItemTypePriceResponse>> {
        return itemTypePriceService.getAll(search)
    }

    @PostMapping("")
    fun create(@Valid @RequestBody data: ItemTypePriceCreateRequest): BaseResponse<ItemTypePriceResponse> {
        return itemTypePriceService.createOrUpdate(price = data.price!!, itemTypeId = data.itemTypeId!!, specId = data.specId!!)
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Long): BaseResponse<ItemTypePriceResponse> {
        return itemTypePriceService.getOne(id)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody data: ItemTypePriceCreateRequest
    ): BaseResponse<ItemTypePriceResponse> {
        return itemTypePriceService.createOrUpdate(id, price = data.price!!, itemTypeId = data.itemTypeId!!, specId = data.specId!!)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): BaseResponse<Nothing?> {
        itemTypePriceService.delete(id)

        return BaseResponse(null, message = "Delete Success")
    }
}