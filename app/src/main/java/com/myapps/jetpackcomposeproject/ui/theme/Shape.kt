package com.myapps.jetpackcomposeproject.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val RoundedCardShape = Shapes(
    large = RoundedCornerShape(
        topStart = 80.dp
    )
)
