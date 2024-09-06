package com.example.demo.services

import com.example.demo.exceptions.DataNotFoundException
import com.example.demo.models.Conditions
import com.example.demo.models.ConditionsRepository
import com.example.demo.models.ItemMerk
import com.example.demo.response.BaseResponse
import com.example.demo.response.ConditionResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
@Slf4j
class ConditionService(
    private val conditionRepository: ConditionsRepository,
    private val itemMerkService: ItemMerkService
) {

    fun getAll(search: String?): BaseResponse<List<ConditionResponse>> {
        var spec = Specification.where<Conditions>(null)
        search?.let {
            spec = spec.and { e, _, cb ->
                cb.like(e.get("name"), "%${it}%")
            }.or {e, _, cb ->
                val itemMerk = e.join<Conditions, ItemMerk>("itemMerk")
                cb.like(itemMerk.get("name"), "%${it}%")
            }
        }

        return BaseResponse(conditionRepository.findAll(spec).map { ConditionResponse(it) })
    }

    fun findOne(id: Long): Conditions {
        return conditionRepository.findByIdOrNull(id) ?: throw DataNotFoundException("Condition Not Found")
    }

    fun getOne(id: Long): BaseResponse<ConditionResponse> {
        return BaseResponse(ConditionResponse(findOne(id)))
    }

    fun createOrUpdate(id: Long? = null, name: String, adjustment: BigDecimal, itemMerkId: Long): BaseResponse<ConditionResponse> {
        val condition = when {
            id != null -> findOne(id)
            else -> Conditions()
        }

        condition.apply {
            this.name = name
            this.adjustment = adjustment
            this.itemMerk = itemMerkService.findOne(itemMerkId)
        }.also { conditionRepository.save(it) }

        return BaseResponse(ConditionResponse(condition))
    }

    fun delete(id: Long) {
        val condition = findOne(id)
        conditionRepository.delete(condition)
    }
}
