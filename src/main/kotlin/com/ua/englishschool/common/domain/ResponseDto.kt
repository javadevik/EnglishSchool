package com.ua.englishschool.common.domain

open class ResponseDto<T>(
        val success: Boolean,
        val payload: T? = null,
        val errors: List<ResponseMessageErrorDto>
)

class SuccessResponseDto<T>(
        payload: T? = null,
        errors: List<ResponseMessageErrorDto> = emptyList()
) : ResponseDto<T>(success = true, payload, errors)

class ErrorResponseDto<T>(
        errors: List<ResponseMessageErrorDto> = emptyList()
) : ResponseDto<T>(success = false, payload = null, errors) {

    constructor(code: Int, message: String) : this(listOf(ResponseMessageErrorDto(code, message)))
}

class ResponseMessageErrorDto(
        val code: Int,
        val message: String
)