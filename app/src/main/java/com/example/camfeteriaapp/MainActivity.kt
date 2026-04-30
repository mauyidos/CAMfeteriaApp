package com.example.camfeteriaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.camfeteriaapp.ui.theme.CAMfeteriaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CAMfeteriaTheme {
                ScreenControl()
            }
        }
    }
}