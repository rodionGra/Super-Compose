package com.supercompose.simple.signup

import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject
import kotlin.random.asKotlinRandom

class SignUpRepository @Inject constructor(

) {

    suspend fun isUsernameAvailable(username: String): Boolean {
        delay(1_000)
        return Random().asKotlinRandom().nextBoolean()
    }

    fun isUsernameCorrect(username: String): Boolean {
        Thread.sleep(1_000)
        return Random().asKotlinRandom().nextBoolean()
    }
}
