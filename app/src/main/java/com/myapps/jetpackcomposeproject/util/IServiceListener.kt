package com.myapps.jetpackcomposeproject.util

interface IServiceListener<T> {
    fun onSuccess(successResult: T)
    fun onError(exception: Exception)
}