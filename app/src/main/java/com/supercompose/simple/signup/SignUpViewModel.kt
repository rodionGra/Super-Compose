package com.supercompose.simple.signup

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    /*Avoid using reactive streams (e.g. StateFlow) to represent your TextField state,
    as these structures introduce asynchronous delays.
    Prefer to use MutableState instead:
    */
    var username by mutableStateOf("")
        private set

    /*If you would still rather use StateFlow to store state, make sure you’re collecting from the flow using the immediate dispatcher as opposed to the default dispatcher.
    This solution requires deeper coroutines knowledge and might lead to issues:
    * Since the collection is synchronous, it’s possible the UI is in a not-ready-to-operate-on state when it happens.
    * Interferes with Compose’s threading and rendering phases, amongst other things, because it assumes that recomposition happens on the Main thread.
    */

    val userNameHasLocalError by derivedStateOf {
        // synchronous call
        //TODO change from main thread
        signUpRepository.isUsernameCorrect(username)
    }

    val userNameHasError: StateFlow<Boolean> =
        snapshotFlow { username }
            .mapLatest { signUpRepository.isUsernameAvailable(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )

    fun updateUsername(input: String) {
        username = input
    }
}
