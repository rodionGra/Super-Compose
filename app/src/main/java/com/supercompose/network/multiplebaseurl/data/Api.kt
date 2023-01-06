package com.supercompose.network.multiplebaseurl.data

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Api(
    val type: ApiType
)
