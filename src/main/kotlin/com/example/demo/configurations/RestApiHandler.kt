package com.example.demo.configurations

import com.example.demo.exceptions.DataNotFoundException
import com.example.demo.response.JsonReturn
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.json.simple.JSONObject
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.io.PrintWriter
import java.io.StringWriter

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class RestApiHandler (
    val jsonReturn: JsonReturn
): ResponseEntityExceptionHandler() {

    val LOG: Logger = LogManager.getLogger("PGILogger")
    val LOG_4xx: Logger = LogManager.getLogger("PGI4xxLogger")

    @ExceptionHandler(DataNotFoundException::class)
    protected fun handleDataNotFoundException(ex: DataNotFoundException, webRequest: WebRequest): ResponseEntity<Any>? {
        val httpHeaders = HttpHeaders()
        return handleExceptionInternal(ex, jsonReturn.generateReturn(false, ex.msg, null), httpHeaders, HttpStatus.NOT_FOUND, webRequest)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {

        val jsonException = JSONObject()
        jsonException["errors"] = ex.bindingResult.allErrors.mapNotNull { it.defaultMessage }

        LOG.error(jsonException)

        return handleExceptionInternal(ex, jsonReturn.generateReturn(false, "Validation Failed", jsonException), headers, status, request)
    }

    fun handleExceptionInternal(ex: Exception, body: Any?, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any>? {
        val jsonException = JSONObject()
        jsonException["className"] = ex.javaClass.simpleName
        jsonException["message"] = ex.message ?: "NO MESSAGE"
        jsonException["string"] = ex.toString()
        jsonException["stacktrace"] = getStackTrace(ex)

        if(status.is4xxClientError) {
            LOG_4xx.error(jsonException)
        }
        else {
            LOG.error(jsonException)
        }

        headers.set("Content-Type", "application/json")
        return super.handleExceptionInternal(ex, body, headers, status, request)
    }

    //https://stackoverflow.com/questions/22271099/convert-exception-to-json
    private fun getStackTrace(throwable: Throwable) : String {
        val sw = StringWriter()
        val pw = PrintWriter(sw, true)
        throwable.printStackTrace(pw)
        return sw.buffer.toString()
    }
}