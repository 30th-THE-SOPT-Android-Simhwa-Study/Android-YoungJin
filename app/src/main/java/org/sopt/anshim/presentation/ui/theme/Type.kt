package org.sopt.anshim.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.sopt.anshim.R

private val Montserrat = FontFamily(
    Font(R.font.montserrat_r),
    Font(R.font.montserrat_b, FontWeight.W700),
)

val Suit = FontFamily(
    Font(R.font.suit_r),
    Font(R.font.suit_m, FontWeight.Medium),
    Font(R.font.suit_b, FontWeight.Bold),
    Font(R.font.suit_sb, FontWeight.SemiBold),
    Font(R.font.suit_eb, FontWeight.ExtraBold),
)

val AnshimTypography = Typography(
    h4 = TextStyle(
        fontFamily = FontFamily(
            Font(
                R.font.suit_r,
                FontWeight.W500,
                FontStyle.Normal
            )
        ),
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = Suit,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = Suit,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Suit,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Suit,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = Suit,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = Suit,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = Suit,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)
// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)


