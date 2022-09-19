package com.supercompose.simple.custommodifier

import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density

// We create the class to hold the alignment info. This class must inherit from ParentDataModifier as that will allow us to retrieve it during the layout of the composable.
internal class InfoLabelsData(
    val alignment: InfoAlignment,
) : ParentDataModifier {

    // The only method ParentDataModifier interface defines is modifyParentData which must return the parent data from the modifier chain. Here we simply return the InfoLabelsData instance.
    override fun Density.modifyParentData(parentData: Any?) = this@InfoLabelsData

    // As we defined our InfoLabelsScope as Stable we have to provide some guarantees which basically boil down to providing equals and hashCode — marking the interface as stable will allow the compiler to do some under the hood optimizations.
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val otherModifier = other as? InfoLabelsData ?: return false

        return alignment == otherModifier.alignment
    }

    //We provide a hashCode to honor the Stable declaration
    override fun hashCode(): Int {
        return alignment.hashCode()
    }

    //We also override toString to have a nicer print statement if we log our scope.
    override fun toString(): String =
        "InfoLabelsData(alignment=$alignment)"
}
