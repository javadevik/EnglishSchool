package com.ua.englishschool.common.dto

open class ResponseDto<T>(
        val success: Boolean,
        val payload: T,
        val errors: List<ResponseMessageErrorDto>
)

class SuccessResponseDto<T>(
        payload: T,
        errors: List<ResponseMessageErrorDto>
) : ResponseDto<T>(success = true, payload, errors)

class ResponseMessageErrorDto(
        val code: Int,
        val message: String
)