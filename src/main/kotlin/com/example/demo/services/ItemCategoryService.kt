package com.example.demo.services

import com.example.demo.exceptions.DataNotFoundException
import com.example.demo.models.ItemCategory
import com.example.demo.models.ItemCategoryRepository
import com.example.demo.response.BaseResponse
import com.example.demo.response.ItemCategoryResponse
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ItemCategoryService (
    val itemTypeRepository: ItemCategoryRepository
) {
    fun getAll(search: String?): BaseResponse<List<ItemCategoryResponse>> {
        var spec = Specification.where<ItemCategory>(null)
        search?.let {
            spec = spec.and { e, _, cb -> cb.like(e.get("name"), "%${search}%") }
        }

        return BaseResponse(itemTypeRepository.findAll(spec).map { ItemCategoryResponse(it) })
    }

    fun findOne(id: Long): ItemCategory {
        return itemTypeRepository.findByIdOrNull(id) ?: throw DataNotFoundException("Item Category Not Found")
    }

    fun getOne(id: Long): BaseResponse<ItemCategoryResponse> {
        return BaseResponse(ItemCategoryResponse(findOne(id)))
    }

    fun createOrUpdate(id: Long? = null, name: String): BaseResponse<ItemCategoryResponse> {
        val itemType = when {
            id != null -> findOne(id)
            else -> ItemCategory()
        }

        itemType.name = name

        itemTypeRepository.save(itemType)

        return BaseResponse(ItemCategoryResponse(itemType))
    }

    fun delete(id: Long) {
        val itemType = findOne(id)
        itemTypeRepository.delete(itemType)
    }
}