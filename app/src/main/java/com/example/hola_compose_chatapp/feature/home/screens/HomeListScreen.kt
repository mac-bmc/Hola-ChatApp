@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.hola_compose_chatapp.feature.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hola_compose_chatapp.R
import com.example.hola_compose_chatapp.model.ChatItemModel
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.ui.organism.ChatItemRow
import com.example.hola_compose_chatapp.ui.organism.CustomAppBar
import com.example.hola_compose_chatapp.ui.organism.CustomHomeBottomBar

@Composable
fun HomeChatList(navController: NavController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bg_color)),
        topBar = { CustomAppBar() },
        bottomBar = {
            CustomHomeBottomBar(navController)
        }
    )
    { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        )
       {
            item {
                ChatItemRow(
                    chatItem = ChatItemModel(
                        UserModel("", "Mac", ""), "Hi How r u", "10.00AM"
                    )
                )
            }

        }
    }


}