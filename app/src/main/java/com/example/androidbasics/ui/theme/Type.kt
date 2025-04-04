package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.androidbasics.R


val cabinFamily = FontFamily(Font(R.font.cabin_bold), Font(R.font.cabin_regular))
val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = cabinFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = cabinFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = cabinFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    displaySmall = TextStyle(
        fontFamily = cabinFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
)
