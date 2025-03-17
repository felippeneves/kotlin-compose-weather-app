package com.felippeneves.kotlin_compose_weather_app.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felippeneves.kotlin_compose_weather_app.R
import com.felippeneves.kotlin_compose_weather_app.presentation.navigation.NavigationManager
import com.felippeneves.kotlin_compose_weather_app.ui.theme.Amber800

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    showBackButton: Boolean = true,
    showSearchButton: Boolean = false,
    showFavoriteButton: Boolean = false,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    Surface(shadowElevation = 5.dp) {
        TopAppBar(
            title = {
                Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            },
            navigationIcon = {
                if (showBackButton) {
                    IconButton(onClick = { NavigationManager.navigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            },
            actions = {

                if (showSearchButton) {
                    IconButton(onClick = { NavigationManager.navigateToSearchCities() }) {
                        Icon(Icons.Default.Search, contentDescription = null)
                    }
                }

                if (showFavoriteButton) {
                    val icon = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder
                    val tint = if (isFavorite) Color.Red else LocalContentColor.current

                    IconButton(onClick = onFavoriteClick) {
                        Icon(icon, contentDescription = null, tint = tint)
                    }
                }

                Box {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = null)
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        containerColor = Amber800
                    ) {
                        DropdownMenuItem(
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                    Text(
                                        text = stringResource(id = R.string.favorites),
                                        modifier = Modifier.padding(start = 8.dp),
                                        color = Color.White
                                    )
                                }
                            },
                            onClick = {
                                expanded = false
                                NavigationManager.navigateToFavoriteCities()
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                    Text(
                                        text = stringResource(id = R.string.settings),
                                        modifier = Modifier.padding(start = 8.dp),
                                        color = Color.White
                                    )
                                }
                            },
                            onClick = {
                                expanded = false
                                NavigationManager.navigateToSettings()
                            }
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Amber800,
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White
            )
        )
    }
}


@Preview
@Composable
private fun Preview() {
    CustomTopAppBar(
        title = "Weather App",
        showBackButton = true,
        showSearchButton = true,
        showFavoriteButton = true,
        isFavorite = false,
        onFavoriteClick = {}
    )
}
