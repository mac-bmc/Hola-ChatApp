package com.example.hola_compose_chatapp.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@HiltAndroidApp
@ExperimentalCoroutinesApi
open class HolaChatApp:Application() {
}