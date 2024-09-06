package com.example.demo.services


import com.example.demo.exceptions.DataNotFoundException
import com.example.demo.models.Imei
import com.example.demo.models.ImeiRepository
import com.example.demo.response.BaseResponse
import com.example.demo.response.ImeiResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Slf4j
class ImeiService(
    private val imeiRepository: ImeiRepository,
    private val specService: SpecService,
    private val itemTypeService: ItemTypeService
) {
    fun getAll(search: String?): BaseResponse<List<ImeiResponse>> {
        var spec = Specification.where<Imei>(null)
        search?.let {
            spec = spec.and { e, _, cb -> cb.like(e.get("name"), "%${search}%") }
        }

        return BaseResponse(imeiRepository.findAll(spec).map { ImeiResponse(it) })
    }

    fun findOne(id: Long?): Imei {
        return imeiRepository.findByIdOrNull(id) ?: throw DataNotFoundException("Item type not found")
    }

    fun findByImei(imei: String): Imei? {
        var spec = Specification.where<Imei>(null)
        spec = spec.and {e, _, cb -> cb.equal(e.get<String>("imei"), imei) }

        return imeiRepository.findOne(spec).orElse(null)
    }

    fun createOrUpdate(id: Long? = null, imei: String, itemTypeId: Long, specId: Long): Imei {
        val imei = when {
            id == null -> Imei()
            else -> findOne(id)
        }.apply {
            this.imei = imei
            this.spec = specService.findOne(specId)
            this.itemType = itemTypeService.findOne(itemTypeId)
        }.also {
            imeiRepository.save(it)
        }

        return imei
    }

    fun delete(id: Long?) {
        val spec = findOne(id)
        imeiRepository.delete(spec)
    }
}