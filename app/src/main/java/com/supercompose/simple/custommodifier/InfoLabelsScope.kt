package com.supercompose.simple.custommodifier

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier

// We create our interface InfoLabelScope that will offer our alignment option during composition.
internal interface InfoLabelsScope {
    @Stable
    // This interface declares an extension function on Modifier that accepts our InfoAlingment defined earlier.
    fun Modifier.align(alignment: InfoAlignment) = this.then(
        // The extension function creates an instance of a private class InfoLabelsData that we will use to hold the alignment information we receive as parameter.
        // Note the use of then to chain our modifier attribute to previous modifiers.
        InfoLabelsData(
            alignment = alignment,
        )
    )

    // As our extension function is scoped to our interface, it is only accessible within classes inheriting from said interface.
    // To be able to call the extension function we will need a concrete implementation of the interface,
    // so we create an instance using the companion object of the interface. This creates an object (effectively a Singleton)
    // that implements the interface; because we do not have any methods in our interface other than the extension method,
    // there is nothing for us to implement in the object.
    companion object : InfoLabelsScope
}
