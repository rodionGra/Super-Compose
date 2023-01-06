package com.supercompose.network.multiplebaseurl.data

object EnvironmentManager {

    val environments = mutableListOf(
        EnvironmentModel(
            apiType = ApiType.AUTH,
            deploymentType = DeploymentType.PRODUCTION
        ),
        EnvironmentModel(
            apiType = ApiType.CONTENT,
            deploymentType = DeploymentType.PRODUCTION
        ),
        EnvironmentModel(
            apiType = ApiType.PAYMENT,
            deploymentType = DeploymentType.PRODUCTION
        ),
    )


    fun getBaseUrl(apiType: ApiType): String {
        return environments.find { it.apiType == apiType }!!.baseUrl
    }
}

data class EnvironmentModel(
    val apiType: ApiType,
    val deploymentType: DeploymentType
) {

    var baseUrl = ""

    init {
        setBaseUrl(apiType, deploymentType)
    }

    fun setBaseUrl(
        apiType: ApiType,
        deploymentType: DeploymentType
    ) {
        val prefix = when (deploymentType) {
            DeploymentType.ALPHA -> "alpha-"
            DeploymentType.BETA -> "beta-"
            DeploymentType.PRODUCTION -> ""
        }
        baseUrl = "https://$prefix${apiType.url}"
    }

    override fun toString(): String {
        return baseUrl
    }

}

enum class DeploymentType {
    PRODUCTION,
    ALPHA,
    BETA
}
