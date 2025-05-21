package com.example.taskaroo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = white,
    onPrimary = primaryColorVariant,

    background = darkBackgroundColor,
    onBackground = white,

    primaryContainer = primaryColorVariant,
    onPrimaryContainer = white,

    onSurface = white, //on all varients of card
    surfaceContainerHighest = darkCardBackgroundColor, //for card
)

private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    onPrimary = white,

    background = liteBackgroundColor,
    onBackground = primaryColor,

    primaryContainer = primaryColor,
    onPrimaryContainer = white,

    onSurface = primaryColor,
    surfaceContainerHighest = liteCardBackgroundColor,

)

@Composable
fun TaskarooTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true, content: @Composable () -> Unit,
) {

    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content
    )
}