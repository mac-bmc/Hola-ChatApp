package com.example.hola_compose_chatapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hola_compose_chatapp.feature.auth.AuthViewModel
import com.example.hola_compose_chatapp.feature.auth.screens.Login
import com.example.hola_compose_chatapp.feature.auth.screens.SignUp
import com.example.hola_compose_chatapp.feature.home.screens.HomeChatList
import com.example.hola_compose_chatapp.feature.home.screens.ProfileScreen

@Composable
fun HomeNavHost(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeChatList(navController)
        }
        composable("profile") {
            ProfileScreen(navController)
        }
    }
}