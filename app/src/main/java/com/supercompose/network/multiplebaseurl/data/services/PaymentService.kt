package com.supercompose.network.multiplebaseurl.data.services

import com.supercompose.network.multiplebaseurl.data.Api
import com.supercompose.network.multiplebaseurl.data.ApiType
import kotlinx.serialization.json.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentService {

    @POST("payment")
    @Api(ApiType.PAYMENT)
    suspend fun pay(
        @Body details: JsonObject
    ): Response<Boolean>
}
