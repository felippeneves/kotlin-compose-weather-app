package com.felippeneves.kotlin_compose_weather_app.presentation.features.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felippeneves.kotlin_compose_weather_app.R
import com.felippeneves.kotlin_compose_weather_app.core.utils.SpeedUtil
import com.felippeneves.kotlin_compose_weather_app.domain.model.WeatherItem

@Composable
fun HumidityWindPressureRow(
    modifier: Modifier = Modifier,
    weather: WeatherItem,
    isImperial: Boolean
) {
    Row(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.humidity}%",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "${weather.pressure} psi",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = SpeedUtil.formatSpeed(weather.speed, isImperial),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {
    HumidityWindPressureRow(
        weather = WeatherItem.createToPreview(),
        isImperial = false
    )
}
