package com.example.presentation.viewModel.viewModel.base

import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity<out VM : BaseViewModel> : AppCompatActivity() {


}