package com.example.hola_compose_chatapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hola_compose_chatapp.feature.auth.AuthViewModel
import com.example.hola_compose_chatapp.feature.auth.screens.Login
import com.example.hola_compose_chatapp.feature.auth.screens.SignUp

@Composable
fun AuthNavHost(authViewModel: AuthViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "signup") {
        composable("signup") {
            SignUp(navController,authViewModel)
        }
        composable("login") {
            Login(navController,authViewModel)
        }
    }
}