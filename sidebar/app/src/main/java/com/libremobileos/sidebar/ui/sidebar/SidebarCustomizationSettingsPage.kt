package com.libremobileos.sidebar.ui.sidebar

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.statusBarsPadding

@Composable
fun SidebarCustomizationSettingsPage(
    sharedPrefs: SharedPreferences,
    onBack: () -> Unit = {}
) {
    var transparency by remember { mutableStateOf(sharedPrefs.getFloat("slider_transparency", 1.0f)) }
    var sliderLength by remember { mutableStateOf(sharedPrefs.getInt("slider_length", 200)) }
    var position by remember { mutableStateOf(sharedPrefs.getInt("sideline_position_x", 1)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Text(text = "Customize Sidebar Slider", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Transparency: ${"%.2f".format(transparency)}")
        Slider(
            value = transparency,
            onValueChange = { transparency = it },
            valueRange = 0.1f..1.0f,
            steps = 8,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                sharedPrefs.edit().putFloat("slider_transparency", transparency).apply()
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Save Transparency")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Slider Length (Height): $sliderLength px")
        Slider(
            value = sliderLength.toFloat(),
            onValueChange = { sliderLength = it.toInt() },
            valueRange = 100f..500f,
            steps = 8,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                sharedPrefs.edit().putInt("slider_length", sliderLength).apply()
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Save Slider Length")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Position: " + if (position == 1) "Right" else "Left")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                position = -1
                sharedPrefs.edit().putInt("sideline_position_x", position).apply()
            }) {
                Text("Left")
            }
            Button(onClick = {
                position = 1
                sharedPrefs.edit().putInt("sideline_position_x", position).apply()
            }) {
                Text("Right")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Back")
        }
    }
}
