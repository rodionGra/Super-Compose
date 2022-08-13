package com.tutorial.supercompose.themes.ui.entity

import com.tutorial.supercompose.navigation.Screen

sealed class ComposeTheme(
    open val id: Float,
    open val name: String
) {
    data class Chapter(
        override val id: Float,
        override val name: String,
        val composeThemes: List<ComposeTheme>
    ) : ComposeTheme(id, name) {

        override fun getItem(itemIndex: Float): ComposeTheme? {
            if (this.id == itemIndex) return this
            composeThemes.forEach {
                val t = it.getItem(itemIndex)
                t?.let { return@getItem t }
            }
            return null
        }
    }

    data class Topic(
        override val id: Float,
        override val name: String,
        val screen: Screen
    ) : ComposeTheme(id, name) {

        override fun getItem(itemIndex: Float) =
            takeIf { it.id == itemIndex }
    }

    abstract fun getItem(itemIndex: Float): ComposeTheme?
}
