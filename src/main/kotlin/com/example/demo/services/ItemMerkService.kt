package com.example.demo.services

import com.example.demo.exceptions.DataNotFoundException
import com.example.demo.models.ItemMerk
import com.example.demo.models.ItemMerkRepository
import com.example.demo.response.BaseResponse
import com.example.demo.response.ItemMerkResponse
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ItemMerkService (
    val itemMerkRepository: ItemMerkRepository,
    val itemCategoryService: ItemCategoryService
) {
    fun getAll(search: String?): BaseResponse<List<ItemMerkResponse>> {
        var spec = Specification.where<ItemMerk>(null)
        search?.let {
            spec = spec.and { e, _, cb -> cb.like(e.get("name"), "%${search}%") }
        }

        return BaseResponse(itemMerkRepository.findAll(spec).map { ItemMerkResponse(it) })
    }

    fun findOne(id: Long): ItemMerk {
        return itemMerkRepository.findByIdOrNull(id) ?: throw DataNotFoundException("Item Merk Not Found")
    }

    fun getOne(id: Long): BaseResponse<ItemMerkResponse> {
        return BaseResponse(ItemMerkResponse(findOne(id)))
    }

    fun createOrUpdate(id: Long? = null, name: String, itemTypeId: Long): BaseResponse<ItemMerkResponse> {
        val itemMerk = when {
            id != null -> findOne(id)
            else -> ItemMerk()
        }

        val itemType = itemCategoryService.findOne(itemTypeId)

        itemMerk.name = name
        itemMerk.itemCategory = itemType

        itemMerkRepository.save(itemMerk)

        return BaseResponse(ItemMerkResponse(itemMerk))
    }

    fun delete(id: Long) {
        val itemMerk = findOne(id)
        itemMerkRepository.delete(itemMerk)
    }
}