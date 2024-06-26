package com.example.drawinggame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.drawinggame.R
import com.example.drawinggame.ui.theme.MyGreen
import com.example.drawinggame.ui.theme.MyLightBlue
import com.example.drawinggame.ui.theme.MyOrange
import com.example.drawinggame.ui.theme.MyPink
import com.example.drawinggame.ui.theme.MyPurple
import com.example.drawinggame.ui.theme.MyYellow

@Composable
fun BottomPanel(
    onClickColor: (Color) -> Unit,
    onLineWidthChange: (Float) -> Unit,
    onClickPattern: (StrokeCap) -> Unit,
    onBackClick: () -> Unit,
    onClearClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(top = 10.dp, bottom = 10.dp, start = 40.dp, end = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColorList { color ->
            onClickColor(color)
        }
        CustomSlider { lineWidth ->
            onLineWidthChange(lineWidth)
        }
        StyleList { pattern ->
            onClickPattern(pattern)
        }
        ButtonPanel(
            onClickBack = { onBackClick() },
            onClickClear = { onClearClick() }
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ColorList(onClick: (Color) -> Unit) {

    val colors = listOf(
        Color.Black,
        Color.White,
        Color.Red,
        MyOrange,
        MyYellow,
        MyGreen,
        MyLightBlue,
        Color.Blue,
        MyPurple,
        MyPink,
    )

    LazyRow(
        modifier = Modifier.padding(5.dp)
    )
    {
        items(colors) { color ->
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable {
                        onClick(color)
                    }
                    .size(32.dp)
                    .background(color, CircleShape)
            )
        }
    }
}

@Composable
fun CustomSlider(onChange: (Float) -> Unit) {

    var position by remember {
        mutableStateOf(0.05f)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text("Line width: ${(position * 100).toInt()}")
        Slider(
            value = position,
            onValueChange = {
                val tempPos = if (it > 0) it else 0.01f
                position = tempPos
                onChange(tempPos * 100)
            }
        )
    }
}

@Composable
fun StyleList(onClick: (StrokeCap) -> Unit) {

    val patterns = listOf(
        StrokeCap.Butt to R.drawable.line,
        StrokeCap.Round to R.drawable.circle,
        StrokeCap.Square to R.drawable.square,
    )

    LazyRow(
        modifier = Modifier.padding(5.dp)
    )
    {
        items(patterns) { pattern ->
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable {
                        onClick(pattern.first)
                    }
                    .size(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = pattern.second),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ButtonPanel(onClickBack: () -> Unit, onClickClear: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White),
            onClick = { onClickBack() }
        )
        {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
        IconButton(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White),
            onClick = { onClickClear() }
        )
        {
            Icon(
                Icons.Default.Refresh,
                contentDescription = null
            )
        }
    }
}