package com.felippeneves.kotlin_compose_weather_app.presentation.navigation

private object ScreensNames {
    const val HOME_SCREEN = "HomeScreen"
    const val SEARCH_CITY_SCREEN = "SearchCityScreen"
    const val FAVORITE_CITIES_SCREEN = "FavoriteCitiesScreen"
    const val SETTINGS_SCREEN = "SettingsScreen"
}

sealed class Screens(val route: String) {
    data object Home : Screens(ScreensNames.HOME_SCREEN)
    data object SearchCity : Screens(ScreensNames.SEARCH_CITY_SCREEN)
    data object FavoriteCities : Screens(ScreensNames.FAVORITE_CITIES_SCREEN)
    data object Settings : Screens(ScreensNames.SETTINGS_SCREEN)
}
