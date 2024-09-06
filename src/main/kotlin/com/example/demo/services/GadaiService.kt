package com.example.demo.services


import com.example.demo.exceptions.DataNotFoundException
import com.example.demo.models.*
import com.example.demo.response.BaseResponse
import com.example.demo.response.GadaiResponse
import com.example.demo.utils.DateUtils
import lombok.extern.slf4j.Slf4j
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
@Slf4j
class GadaiService(
    private val gadaiRepository: GadaiRepository,
    private val imeiService: ImeiService,
    private val itemTypePriceRepository: ItemTypePriceRepository,
    private val conditionService: ConditionService,
    private val itemTypeService: ItemTypeService,
    private val specService: SpecService
) {

    fun getAll(search: String?): BaseResponse<List<GadaiResponse>> {
        var spec = Specification.where<Gadai>(null)
        search?.let {
            spec = spec.and { e, _, cb -> cb.like(e.get("name"), "%${search}%") }
        }

        return BaseResponse(gadaiRepository.findAll(spec).map { GadaiResponse(it) })
    }

    fun findOne(id: Long): Gadai {
        return gadaiRepository.findByIdOrNull(id) ?: throw DataNotFoundException("Item Category Not Found")
    }

    fun getOne(id: Long): BaseResponse<GadaiResponse> {
        return BaseResponse(GadaiResponse(findOne(id)))
    }

    fun createOrUpdate(id: Long? = null, itemTypeId: Long, conditionId: Long, imei: String): BaseResponse<GadaiResponse> {
        val gadai = when {
            id != null -> findOne(id)
            else -> Gadai()
        }

        val imeiEntity = imeiService.findByImei(imei)
        val spec = imeiEntity?.spec
            ?: specService.findOne(SpecTypeEnum.RESMI.id)

        // insert to imei if imei is null
        if (imeiEntity == null) {
            imeiService.createOrUpdate(null, imei, itemTypeId, spec.id)
        }

        val condition = conditionService.findOne(conditionId)

        val itemTypePrice = itemTypePriceRepository.findOneByItemTypeIdEqualsAndSpecIdEquals(itemTypeId, spec.id)
        val itemType = itemTypeService.findOne(itemTypeId)

        gadai.condition = condition
        gadai.itemType = itemType
        gadai.priceBeforeAdjustment = itemTypePrice.price
        gadai.price = (itemTypePrice.price.toBigDecimal() * condition.adjustment / 100.toBigDecimal()).toInt()
        gadai.imei = imei
        gadai.spec = spec
        gadai.date = DateUtils.getCurrentDate()

        gadaiRepository.save(gadai)

        return BaseResponse(GadaiResponse(gadai))
    }

    fun delete(id: Long) {
        val itemType = findOne(id)
        gadaiRepository.delete(itemType)
    }
}