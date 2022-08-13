package com.tutorial.supercompose.themes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutorial.supercompose.themes.data.ComposeThemesRepository
import com.tutorial.supercompose.themes.ui.entity.ComposeTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComposeThemasViewModel @Inject constructor(
    private val composeThemesRepository: ComposeThemesRepository
) : ViewModel() {

    private val _composeThemeFlow =
        MutableStateFlow<ComposeTheme.Chapter?>(composeThemesRepository.composeThemesList)
    val composeThemesFlow: StateFlow<ComposeTheme.Chapter?>
        get() = _composeThemeFlow

    fun getComposeThemeById(id: Float) {
        viewModelScope.launch {
            _composeThemeFlow.emit(composeThemesRepository.getById(id))
        }
    }
}
