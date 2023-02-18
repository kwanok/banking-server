package com.numble.banking.error

import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorHandler {
    @ExceptionHandler(ApiException::class)
    fun handleCustomException(e: ApiException): ErrorResponse {
        val response = ErrorResponse()
        response.code = e.code.name
        response.message = e.code.message
        response.details = e.details
        return response
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleMethodArgumentNotValidException(e: HttpMessageNotReadableException): ErrorResponse {
        val response = ErrorResponse()
        response.code = "INVALID_REQUEST"
        response.message = "필수 요청 파라미터가 누락되었습니다."
        response.details = null
        return response
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ErrorResponse {
        val response = ErrorResponse()
        response.code = "BAD_REQUEST"
        response.message = e.bindingResult.allErrors[0].defaultMessage
        response.details = e.detailMessageArguments
        return response
    }
}