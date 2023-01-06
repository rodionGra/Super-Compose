package com.supercompose

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.supercompose.network.multiplebaseurl.data.BaseUrlInterceptor
import com.supercompose.network.multiplebaseurl.data.services.AuthService
import com.supercompose.network.multiplebaseurl.data.services.ContentService
import com.supercompose.network.multiplebaseurl.data.services.PaymentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@HiltAndroidApp
class App : Application()


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }


    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(BaseUrlInterceptor()).build()
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
    ): Retrofit {
        val contentType = "application/json".toMediaType()


        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl("https://someauth.baseurl.com")
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(
        retrofit: Retrofit
    ): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideContentService(
        retrofit: Retrofit
    ): ContentService {
        return retrofit.create(ContentService::class.java)
    }

    @Provides
    @Singleton
    fun providePaymentService(
        retrofit: Retrofit
    ): PaymentService {
        return retrofit.create(PaymentService::class.java)
    }
}
