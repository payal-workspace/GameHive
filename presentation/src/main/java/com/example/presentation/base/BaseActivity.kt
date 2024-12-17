package com.example.presentation.base

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity<out VM : BaseViewModel> : AppCompatActivity() {


}