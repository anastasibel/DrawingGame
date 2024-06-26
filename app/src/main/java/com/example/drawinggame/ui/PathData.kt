package com.example.drawinggame.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.DefaultStrokeLineCap

data class PathData(
    val path: Path = Path(),
    val color: Color = Color.Black,
    val lineWidth: Float = 5f,
    val pattern: StrokeCap = DefaultStrokeLineCap,
)
