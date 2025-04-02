package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.androidbasics.R


val AbrilFatface = FontFamily(Font(R.font.abril_fatface_regular))
val Montserrat = FontFamily(Font(R.font.montserrat_bold), Font(R.font.montserrat_regular))

val AppTypography =
    Typography(
        displayLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            fontFamily = AbrilFatface
        ),
        displayMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            fontFamily = Montserrat
        ),
        labelSmall = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            fontFamily = Montserrat
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            fontFamily = Montserrat
        ),

        )

