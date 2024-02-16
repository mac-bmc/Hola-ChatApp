package com.example.hola_compose_chatapp.feature.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hola_compose_chatapp.model.ChatItemModel
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.ui.organism.ChatItemRow
import com.example.hola_compose_chatapp.ui.organism.CustomAppBar
import com.example.hola_compose_chatapp.ui.organism.CustomHomeBottomBar

@Composable
fun ProfileScreen(navController: NavController)
{
    Scaffold(
        topBar = { CustomAppBar()},
        bottomBar = { CustomHomeBottomBar(navController)}
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            ChatItemRow(
                chatItem = ChatItemModel(
                    UserModel("", "Mac", ""), "Hi How r u", "10.00AM"
                )
            )
        }
    }
}