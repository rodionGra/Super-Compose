package com.supercompose.network.multiplebaseurl.data.services

import com.supercompose.network.multiplebaseurl.data.Api
import com.supercompose.network.multiplebaseurl.data.ApiType
import retrofit2.Response
import retrofit2.http.GET

interface ContentService {

    @GET("content")
    @Api(ApiType.CONTENT)
    suspend fun getContent(): Response<List<String>>
}
