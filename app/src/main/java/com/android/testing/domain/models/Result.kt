package com.android.testing.domain.models

sealed interface Result<out T, out E : DomainError> {
    data class Success<out T>(val data: T) : Result<T, Nothing>
    data class Error<out E : DomainError>(val error: E) : Result<Nothing, E>
}

val Result<Any, DomainError>.isSuccess
    get() = this is Result.Success