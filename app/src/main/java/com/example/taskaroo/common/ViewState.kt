package com.example.taskaroo.common


sealed class ViewState<T>(val message: String? = null, val data: T? = null) {

    class Loading<T>(data: T? = null) : ViewState<T>(data = data)

    class Success<T>(data: T? = null) : ViewState<T>(data = data)

    class Error<T>(data: T? = null, message: String) : ViewState<T>(message = message.toString(), data = data)

}
