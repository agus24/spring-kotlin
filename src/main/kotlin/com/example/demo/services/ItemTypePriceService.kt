package com.example.demo.services


import com.example.demo.exceptions.DataNotFoundException
import com.example.demo.models.ItemType
import com.example.demo.models.ItemTypePrice
import com.example.demo.models.ItemTypePriceRepository
import com.example.demo.response.BaseResponse
import com.example.demo.response.ItemTypePriceResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Slf4j
class ItemTypePriceService(
    private val itemTypePriceRepository: ItemTypePriceRepository,
    private val specService: SpecService,
    private val itemTypeService: ItemTypeService
) {
    fun getAll(search: String?): BaseResponse<List<ItemTypePriceResponse>> {
        var spec = Specification.where<ItemTypePrice>(null)
        search?.let {
            spec = spec.and { e, _, cb -> cb.like(e.get("name"), "%${search}%") }
        }

        return BaseResponse(itemTypePriceRepository.findAll(spec).map { ItemTypePriceResponse(it) })
    }

    fun findOne(id: Long): ItemTypePrice {
        return itemTypePriceRepository.findByIdOrNull(id) ?: throw DataNotFoundException("Item Merk Not Found")
    }

    fun getOne(id: Long): BaseResponse<ItemTypePriceResponse> {
        return BaseResponse(ItemTypePriceResponse(findOne(id)))
    }

    fun createOrUpdate(id: Long? = null, price: Int, specId: Long, itemTypeId: Long): BaseResponse<ItemTypePriceResponse> {
        val itemTypePrice = when {
            id != null -> findOne(id)
            else -> ItemTypePrice()
        }.apply {
            this.price = price
            this.spec = specService.findOne(specId)
            this.itemType = itemTypeService.findOne(itemTypeId)
        }.also {
            itemTypePriceRepository.save(it)
        }

        return BaseResponse(ItemTypePriceResponse(itemTypePrice))
    }

    fun delete(id: Long) {
        val itemTypePrice = findOne(id)
        itemTypePriceRepository.delete(itemTypePrice)
    }
}