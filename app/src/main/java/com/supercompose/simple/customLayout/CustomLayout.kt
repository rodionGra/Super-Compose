package com.supercompose.simple.customLayout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalGrid(
    columns: Int,
    itemsSpacing: Dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        // Розрахунок доступної ширини айтема базуючись на загальній ширині екрану, кількості стовпчиків та заданому відступі між елементами
        val spacingPx = itemsSpacing.roundToPx()
        val totalHorizontalSpacing = spacingPx * (columns - 1)
        val itemWidth = (constraints.maxWidth - totalHorizontalSpacing) / columns
        // Створюємо Constraints з фіксованою шириною щоб заміряти айтеми. Використовуємо щойно пораховану ширину як мінімальне і максимальне значення, щоб впевнитись що айтеми завжди будуть займати відведений під них простір.
        val itemConstraints = Constraints(
            minWidth = itemWidth,
            maxWidth = itemWidth,
        )

        // Заміряємо айтеми з щойно створеними constraints з фіксованою шириною
        val placeables = measurables.map { it.measure(itemConstraints) }

        // Для спрощення розділяємо список placeables на список списків, де кожен вкладений список репрезентує окремий рядок в сітці
        val placeablesByRows = placeables.chunked(columns)
        // Для майбутніх розрахунків нам треба знати висоту найвищого айтема в кожному рядку. Для цього ми готуємо Map<Індекс рядка, Максимальна висота>
        val heightByRow = placeablesByRows.mapIndexed { rowIndex, rowPlaceables ->
            rowIndex to rowPlaceables.maxOf { it.height }
        }.toMap()

        // Рахуємо загальну висоту нашого layout використовуючи значення максимальної висоти з кожного рядка (які ми щойно порахували) та порахувавши скільки місця потрібно на відступи між айтемами
        val totalSpacingVertical = spacingPx * (placeablesByRows.size - 1)
        val totalHeight = heightByRow.values.sum() + totalSpacingVertical

        // Змінна в якій буде зберігатись поточне значення 'y' координати для розміщення айтемів
        var y = 0
        // Використовуємо максимальну ширину з вхідних constraints та пораховану загальну висоту щоб задати розміри layout
        layout(constraints.maxWidth, totalHeight) {
            // проходимось по кожному рядку
            placeablesByRows.forEachIndexed { rowIndex, rowPlaceables ->
                // Змінна в якій буде зберігатись поточне значення 'x' координати для розміщення айтемів в межах одного рядка
                var x = 0
                // Для кожного айтема в рядку
                rowPlaceables.forEach { placeable ->
                    // розташовуємо айтем використовуючі поточні координати x та y
                    placeable.placeRelative(x, y)
                    // і додаємо ширину айтема та відступ до поточного значення координати 'x'
                    x += itemWidth + spacingPx
                }

                // коли всі айтеми в рядку розміщені додаємо до поточного значення координати 'y' висоту поточного рядка та відступ
                y += requireNotNull(heightByRow[rowIndex]) + spacingPx
            }
        }
    }
}

@Preview
@Composable
fun CustomLayoutPreview() {
    Surface(color = Color(0xFFF8F8F8)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            VerticalGrid(
                columns = 3,
                itemsSpacing = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 40.dp)
            ) {
                (1..9).forEach {
                    Card {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 12.dp)
                        ) {
                            Text(
                                text = it.toString(),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}
