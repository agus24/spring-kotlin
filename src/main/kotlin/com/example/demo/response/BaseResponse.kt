package com.example.demo.response

open class BaseResponse<T>(
    var data: T,
    val status: Boolean = true,
    val message: String? = null
)