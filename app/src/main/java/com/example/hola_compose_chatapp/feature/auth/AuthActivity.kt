package com.example.hola_compose_chatapp.feature.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.hola_compose_chatapp.navigation.AuthNavHost
import com.example.hola_compose_chatapp.ui.theme.HolaComposeChatAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        setContent {
            HolaComposeChatAppTheme {
                AuthNavHost(authViewModel)
            }
        }
    }

}





