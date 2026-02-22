package com.example.petdata



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.petdata.ui.screens.HomeScreen
import com.example.petdata.ui.theme.RescateAnimalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RescateAnimalTheme {
                HomeScreen()
            }
        }
    }
}