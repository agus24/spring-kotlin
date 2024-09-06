package com.example.demo.services

import com.example.demo.exceptions.DataNotFoundException
import com.example.demo.models.ItemMerkRepository
import com.example.demo.models.ItemType
import com.example.demo.models.ItemTypeRepository
import com.example.demo.response.BaseResponse
import com.example.demo.response.ItemTypeResponse
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ItemTypeService(
    val itemTypeRepository: ItemTypeRepository,
    private val itemMerkService: ItemMerkService
) {
    fun getAll(search: String?): BaseResponse<List<ItemTypeResponse>> {
        var spec = Specification.where<ItemType>(null)
        search?.let {
            spec = spec.and { e, _, cb -> cb.like(e.get("name"), "%${search}%") }
        }

        return BaseResponse(itemTypeRepository.findAll(spec).map { ItemTypeResponse(it) })
    }

    fun findOne(id: Long?): ItemType {
        return itemTypeRepository.findByIdOrNull(id) ?: throw DataNotFoundException("Item type not found")
    }

    fun getOne(id: Long): BaseResponse<ItemTypeResponse> {
        return BaseResponse(ItemTypeResponse(findOne(id)))
    }

    fun createOrUpdate(id: Long? = null, name: String, itemMerkId: Long): BaseResponse<ItemTypeResponse> {
        val itemType = when {
            id == null -> ItemType()
            else -> findOne(id)
        }

        val itemMerk = itemMerkService.findOne(itemMerkId)

        itemType.name = name
        itemType.itemMerk = itemMerk

        itemTypeRepository.save(itemType)

        return BaseResponse(ItemTypeResponse(itemType))
    }

    fun delete(id: Long?) {
        val itemType = findOne(id)
        itemTypeRepository.delete(itemType)
    }
}