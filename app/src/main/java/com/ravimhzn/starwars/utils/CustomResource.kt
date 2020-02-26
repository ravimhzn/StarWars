package com.ravimhzn.starwars.utils


sealed class CustomResource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : CustomResource<T>(data)
    class Loading<T>(data: T? = null) : CustomResource<T>(data)
    class Error<T>(message: String, data: T? = null) : CustomResource<T>(data, message)
}
