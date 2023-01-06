package com.supercompose.network.multiplebaseurl.data.services

import com.supercompose.network.multiplebaseurl.data.Api
import com.supercompose.network.multiplebaseurl.data.ApiType
import com.supercompose.network.multiplebaseurl.data.AuthModel
import kotlinx.serialization.json.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

    @POST("login")
    @Api(ApiType.AUTH)
    suspend fun login(
        @Body credentials: JsonObject
    ): Response<AuthModel>
}

