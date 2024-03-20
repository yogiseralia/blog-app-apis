package com.yogeshseralia.blogappapis.exceptions

import com.yogeshseralia.blogappapis.payloads.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
    import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundExceptionHandler(exception: ResourceNotFoundException): ResponseEntity<ApiResponse> {
        val apiResponse = ApiResponse(exception.message.orEmpty(), false)
        return ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgsNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val body = hashMapOf<String, String>()
        ex.bindingResult.allErrors.forEach { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val defaultMessage = error.defaultMessage
            body[fieldName] = defaultMessage.orEmpty()
        }
        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }
}