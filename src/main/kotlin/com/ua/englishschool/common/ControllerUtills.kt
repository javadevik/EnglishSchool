package com.ua.englishschool.common

import com.ua.englishschool.common.dto.ErrorResponseDto
import com.ua.englishschool.common.dto.ResponseDto
import com.ua.englishschool.common.errors.AlreadyExistsError
import com.ua.englishschool.common.errors.NotFoundError
import com.ua.englishschool.common.errors.OperationError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun <T> NotFoundError.toNotFoundResponse(): ResponseEntity<ResponseDto<T>> {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponseDto(HttpStatus.NOT_FOUND.value(), this.message))
}

fun <T> AlreadyExistsError.toAlreadyExistsResponse(): ResponseEntity<ResponseDto<T>> {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), this.message))
}

fun <T> unknownErrorResponse(): ResponseEntity<ResponseDto<T>> {
    return ResponseEntity.internalServerError()
            .body(ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "UNKNOWN_ERROR"))
}

fun <T> mapErrors(operationError: OperationError): ResponseEntity<ResponseDto<T>> {
    return when (operationError) {
        is NotFoundError -> operationError.toNotFoundResponse()
        is AlreadyExistsError -> operationError.toAlreadyExistsResponse()
        else -> unknownErrorResponse()
    }
}