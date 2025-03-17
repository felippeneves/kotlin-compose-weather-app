package com.felippeneves.kotlin_compose_weather_app.presentation.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.felippeneves.kotlin_compose_weather_app.core.utils.ApiUtil
import com.felippeneves.kotlin_compose_weather_app.core.utils.DateUtil
import com.felippeneves.kotlin_compose_weather_app.core.utils.TemperatureFormatter
import com.felippeneves.kotlin_compose_weather_app.domain.model.WeatherItem

@Composable
fun DailyWeatherItem(
    modifier: Modifier = Modifier,
    weather: WeatherItem,
    isImperial: Boolean
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = CircleShape,
        color = Color(0xFFEEF1EF),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = DateUtil.getDayOfWeek(weather.timestampDate),
                modifier = Modifier.padding(start = 8.dp)
            )
            Image(
                painter = rememberAsyncImagePainter(ApiUtil.buildIconUrl(weather.weatherObject.icon)),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
            )
            Surface(
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(
                    text = weather.weatherObject.description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue.copy(alpha = 0.7f),
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(TemperatureFormatter.format(weather.temp.max, isImperial))
                    }
                    append(" | ")
                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray,
                        )
                    ) {
                        append(TemperatureFormatter.format(weather.temp.min, isImperial))
                    }
                },
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DailyWeatherItem(
        weather = WeatherItem.createToPreview(),
        isImperial = false
    )
}