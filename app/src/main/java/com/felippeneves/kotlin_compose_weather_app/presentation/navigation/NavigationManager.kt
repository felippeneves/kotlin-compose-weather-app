package com.felippeneves.kotlin_compose_weather_app.presentation.navigation

import androidx.navigation.NavController
import java.lang.ref.WeakReference

object NavigationManager {
    private var navControllerRef: WeakReference<NavController>? = null

    fun setNavController(navController: NavController) {
        navControllerRef = WeakReference(navController)
    }

    private fun getNavController(): NavController? = navControllerRef?.get()

    private fun isCurrentDestination(route: String): Boolean =
        getNavController()?.currentDestination?.route == route

    private fun navigateToRoute(route: String) =
        getNavController()?.takeIf { !isCurrentDestination(route) }?.navigate(route)

    fun navigateBack() = getNavController()?.popBackStack()

    fun navigateToSearchCities() = navigateToRoute(Screens.SearchCity.route)

    fun navigateToFavoriteCities() = navigateToRoute(Screens.FavoriteCities.route)

    fun navigateToSettings() = navigateToRoute(Screens.Settings.route)
}
