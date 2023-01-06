package com.supercompose.network.multiplebaseurl.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.supercompose.network.multiplebaseurl.data.ApiType
import com.supercompose.network.multiplebaseurl.data.DeploymentType
import com.supercompose.network.multiplebaseurl.data.EnvironmentManager
import com.supercompose.network.multiplebaseurl.data.EnvironmentModel
import com.supercompose.network.multiplebaseurl.data.services.AuthService
import com.supercompose.network.multiplebaseurl.data.services.ContentService
import com.supercompose.network.multiplebaseurl.data.services.PaymentService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DynamicBaseUrlViewModel @Inject constructor(
    private val authService: AuthService,
    private val contentService: ContentService,
    private val paymentService: PaymentService
) : ViewModel() {

    val deploymentTypes = DeploymentType.values().toList()
    val apiTypes = ApiType.values().toList()

    private val _resultFlow = MutableStateFlow("No results yet")
    val resultFlow = _resultFlow.asStateFlow()

    fun login() {
        doRestApiCall {
            val credentials = JsonObject(
                mapOf(
                    "username" to JsonPrimitive("username"),
                    "password" to JsonPrimitive("password")
                )
            )
            authService.login(credentials)
        }
    }

    fun getContents() {
        doRestApiCall {
            contentService.getContent()
        }
    }

    fun pay() {
        doRestApiCall {
            val details = JsonObject(
                mapOf(
                    "uuid" to JsonPrimitive("11111"),
                    "amount" to JsonPrimitive(100),
                    "deviceId" to JsonPrimitive("11111"),
                )
            )
            paymentService.pay(details)
        }
    }

    private fun <T> doRestApiCall(restCall: suspend () -> Response<T>) {
        viewModelScope.launch {
            try {
                val response = restCall.invoke()
                _resultFlow.value = "Success ${response.raw().request.url}"
            } catch (e: Exception) {
                Log.e("NETWORK_CALL_ERROR", "exception = $e")
                _resultFlow.value = "Exception = ${e.message}"
            }
        }
    }

    fun updateEnvironment(
        apiType: ApiType,
        deploymentType: DeploymentType
    ) {
        val index =
            EnvironmentManager.environments.indexOfFirst { it.apiType == apiType }
        EnvironmentManager.environments[index] = EnvironmentModel(
            apiType = apiType,
            deploymentType = deploymentType
        )
    }
}
