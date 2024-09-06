package com.example.demo.response

import com.fasterxml.jackson.databind.ObjectMapper
import org.json.simple.JSONObject
import org.springframework.stereotype.Service

@Service
class JsonReturn {
    fun generateReturn(status: Boolean, reason: String?, data: JSONObject?) : String {
        val jsonObject = JSONObject()
        jsonObject["status"] = status
        jsonObject["reason"] = reason
        jsonObject["ts"] = System.currentTimeMillis()
        data?.let {
            jsonObject["data"] = it
        } ?: run {
            jsonObject["data"] = JSONObject()
        }

        return jsonObject.toString()
    }
}