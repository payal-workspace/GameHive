package com.example.presentation.utils

sealed class Screens(val route: String) {
    object Page1: Screens("Home")
}
