package com.example.demo.response

import org.json.simple.JSONObject

open class BaseResponse<T>(
    var data: T,
    val status: Boolean = true,
    val message: String? = null
)