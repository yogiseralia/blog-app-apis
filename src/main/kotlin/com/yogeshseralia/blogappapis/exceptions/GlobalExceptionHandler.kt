package com.yogeshseralia.blogappapis.exceptions

import com.yogeshseralia.blogappapis.payloads.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundExceptionHandler(exception: ResourceNotFoundException): ResponseEntity<ApiResponse> {
        val apiResponse = ApiResponse(exception.message.orEmpty(), false)
        return ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND)
    }
}