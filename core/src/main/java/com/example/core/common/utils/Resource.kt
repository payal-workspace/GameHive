package com.example.core.common.utils

import java.io.IOException


sealed class Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val error: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}