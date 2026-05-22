package com.example.camfeteriaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.camfeteriaapp.navigation.ScreenControl
import com.example.camfeteriaapp.ui.theme.CAMfeteriaTheme
import com.example.camfeteriaapp.viewmodel.CAMfeteriaViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val camfeteriaVM: CAMfeteriaViewModel by viewModels()
            CAMfeteriaTheme {
                ScreenControl(camfeteriaVM)
            }
        }
    }
}