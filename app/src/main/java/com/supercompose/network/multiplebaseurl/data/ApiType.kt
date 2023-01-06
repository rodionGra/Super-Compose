package com.supercompose.network.multiplebaseurl.data

/**
 * We defined an enum type for each microservices. They all have a string for the hostname.
 * */

enum class ApiType(
    val url: String
) {
    AUTH("auth.mydomain.com"),
    CONTENT("mydomain.com"),
    PAYMENT("pay.mydomain.com");
}
