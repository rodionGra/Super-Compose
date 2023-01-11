package com.supercompose.kotlinx.serialization

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json

fun main() {
    val start = KotlinxSerializationPart1()
    start.customSerializable()
}

class KotlinxSerializationPart1 {
    fun simpleStart() {
        val data = Data("TestText", 20)

        val jsonString = Json.encodeToString(data)

        val testObject = Json.decodeFromString<Data>(jsonString)
    }

    fun customSerializationClass() {
        val colorStart = Color.Cyan
        val jsonString = Json.encodeToString(ComposeColorAsStringSerializer, colorStart)
        val color = Json.decodeFromString(ComposeColorAsStringSerializer, jsonString)
        println(colorStart)
        println(jsonString)
        println(color)
    }

    fun customSerializable() {
        val colorStart = CustomColor(32640)
        val jsonString = Json.encodeToString(colorStart)
        val customColor = Json.decodeFromString<CustomColor>(jsonString)
        println(colorStart)
        println(jsonString)
        println(customColor)
    }
}

@Serializable
data class Data(val text: String, val age: Int)

@Serializable(with = CustomColorAsStringSerializer::class)
data class CustomColor(val rgb: Int)

object ComposeColorAsStringSerializer : KSerializer<Color> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Color", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Color) {
        val string = value.value.toString()
        encoder.encodeString(string)
    }

    override fun deserialize(decoder: Decoder): Color {
        val string = decoder.decodeString()
        return Color(string.toULong())
    }
}

object CustomColorAsStringSerializer : KSerializer<CustomColor> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Color", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: CustomColor) {
        val string = value.rgb.toString(16).padStart(6, '0')
        encoder.encodeString(string)
    }

    override fun deserialize(decoder: Decoder): CustomColor {
        val string = decoder.decodeString()
        return CustomColor(string.toInt(16))
    }
}

@Serializable
data class ViewData(
    val viewName: String,
    @Serializable(with = CustomColorAsStringSerializer::class) val backgroundColor: CustomColor,
    @Serializable(with = CustomColorAsStringSerializer::class) val textColor: CustomColor
)
