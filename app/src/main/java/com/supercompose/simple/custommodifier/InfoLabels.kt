package com.supercompose.simple.custommodifier

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import kotlin.math.max

@Composable
internal fun InfoLabels(
    modifier: Modifier = Modifier,
    // In our composable we change the signature and make our content an extension function on InfoLabelsScope so that we have access to our alignment extension function on Modifier — this gives us access within our content to the align extension on Modifier.
    content: @Composable InfoLabelsScope.() -> Unit,
) {
    /*
     We use a layout for our root element, this is equivalent to creating a custom ViewGroup in the view system.
     For a layout composable we need to provide the content, an optional modifier and a lambda that will measure
     and place the content based on the incoming constraints; this lambda takes a list of measurables
     and the constraints our content must adhere to.
    */
    Layout(
        // 3
        content = { InfoLabelsScope.content() },
        modifier = modifier,
    ) { measurables, constraints ->
        //For our specific case we require an even number of children (our children must come in pairs of label and description), so we ensure we are provided a valid content or throw.
        require(measurables.size % 2 == 0) { "InfoLabels requires an even number of children" }
        //We make a copy of our incoming constraints with the min width and height set to 0 and we will use that to measure our children.
        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0,
        )
        //Once we have our measurables we walk the list and obtain the alignment for each of them. This uses our helper extension functions defined in step 1. If no alignment was provided it will default to Top.
        val measurableAlignment = measurables.map { measurable ->
            measurable.alignment
        }
        //We measure our children with the updated constraints. This gives us a Placeable for each Measurable that we will later place within the root layout.
        val placeables = measurables.map { measurable ->
            measurable.measure(looseConstraints)
        }
        //We split our content measurables by getting the odd entries in the list, which correspond to the labels.
        val labels = List(placeables.size / 2) { index ->
            placeables[2 * index]
        }
        val labelsAlignment = List(placeables.size / 2) { index ->
            measurableAlignment[2 * index]
        }
        val descriptions = List(placeables.size / 2) { index ->
            placeables[2 * index + 1]
        }
        val descriptionsAlignment = List(placeables.size / 2) { index ->
            measurableAlignment[2 * index + 1]
        }
        //We want to know how wide our labels need to be, so we walk our list of labels and get their widths, and then we get the max of those — that will be our label width.
        val maxLabelWidth = labels.maxByOrNull { it.width }?.width ?: 0
        //Next we want to know how tall our composable needs to be. Because we will stack our label + description combos vertically, we get the height of each combo (the max of the height of the label or the description) and add them all together.
        val height = List(labels.size) { index ->
            Math.max(labels[index].height, descriptions[index].height)
        }.sum()
        //Now that we have all in the info we need to render our composable it’s time to call layout — this will place the composables
        // within our root container. This function takes a width and a height; the width comes from our inherited constraints,
        // and the height is the height we calculated in step 7, but constrained to the maximum height we received in our incoming constraints.
        layout(
            constraints.maxWidth,
            height.coerceAtMost(constraints.maxHeight)
        ) {
            var yPosition = 0

            //We loop over all our composables — we use the labels indices as we know we have the same number of labels and descriptions.
            for (i in labels.indices) {
                // We grab the pieces we need for the layout. What’s changed here is that we get the alignment for the label and description corresponding to the current row.
                val label = labels[i]
                val labelAlignment = labelsAlignment[i]
                val description = descriptions[i]
                val descriptionAlignment = descriptionsAlignment[i]
                val labelHeight = label.height
                val descriptionHeight = description.height

                //For the current row, its height will be the max of the height of the label and the description.
                val cellHeight = max(label.height, description.height)
                // We compute the vertical delta for the label and the description; this is how much taller the current row is relative to the child to lay out. We will use this later to position the children. Because the cell height is the max of the label and description heights, these deltas are always positive or zero.
                val labelDelta = cellHeight - labelHeight
                val descriptionDelta = cellHeight - descriptionHeight
                //The label is placed at the parent start (X coordinate equals to 0) and vertically (Y coordinate) offset by the accumulated height of all previous rows.
                label.place(
                    x = 0,
                    // This is where the main change takes place. Instead of placing the child at the vertical offset corresponding to the accumulated height of all the previous rows, we check which type of alignment has been requested. If the alignment is Top we do as before, place the child at the accumulated vertical offset; if it is Center we add half the difference between the row height and the child height; finally, if it’s Bottom we add the full delta so that the bottom of the child is at the bottom of the row.
                    y = yPosition + when (labelAlignment) {
                        InfoAlignment.Top -> 0
                        InfoAlignment.Center -> labelDelta / 2
                        InfoAlignment.Bottom -> labelDelta
                    }
                )
                //The description is placed at the X offset corresponding to the widest label, and at the same vertical offset as the label.
                description.place(
                    x = maxLabelWidth,
                    y = yPosition + when (descriptionAlignment) {
                        InfoAlignment.Top -> 0
                        InfoAlignment.Center -> descriptionDelta / 2
                        InfoAlignment.Bottom -> descriptionDelta
                    }
                )
                //We increase our accumulated height by the height of the row we just laid out.
                yPosition += cellHeight
            }
        }
    }
}


// We define a couple of helper extension functions on Measurable that will retrieve the InfoLabelsData and InfoAlignment from the measurables we want to lay out.
private val Measurable.infoLabelChildData: InfoLabelsData?
    get() = parentData as? InfoLabelsData

private val Measurable.alignment: InfoAlignment
    get() = infoLabelChildData?.alignment ?: InfoAlignment.Top
