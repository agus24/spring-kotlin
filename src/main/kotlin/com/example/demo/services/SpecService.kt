package com.example.demo.services

import com.example.demo.exceptions.DataNotFoundException
import com.example.demo.models.Spec
import com.example.demo.models.SpecRepository
import com.example.demo.response.BaseResponse
import com.example.demo.response.SpecResponse
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SpecService (
    val specRepository: SpecRepository
) {
    fun getAll(search: String?): BaseResponse<List<SpecResponse>> {
        var specification = Specification.where<Spec>(null)
        search?.let {
            specification = specification.and { e, _, cb -> cb.like(e.get("name"), "%${search}%") }
        }

        return BaseResponse(specRepository.findAll(specification).map { SpecResponse(it) })
    }

    fun findOne(id: Long?): Spec {
        return specRepository.findByIdOrNull(id) ?: throw DataNotFoundException("Item type not found")
    }

    fun getOne(id: Long): BaseResponse<SpecResponse> {
        return BaseResponse(SpecResponse(findOne(id)))
    }

    fun createOrUpdate(id: Long? = null, name: String): BaseResponse<SpecResponse> {
        val spec = when {
            id == null -> Spec()
            else -> findOne(id)
        }

        spec.name = name

        specRepository.save(spec)

        return BaseResponse(SpecResponse(spec))
    }

    fun delete(id: Long?) {
        val spec = findOne(id)
        specRepository.delete(spec)
    }
}