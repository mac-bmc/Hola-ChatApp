@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.hola_compose_chatapp.feature.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hola_compose_chatapp.R
import com.example.hola_compose_chatapp.feature.home.HomeViewModel
import com.example.hola_compose_chatapp.ui.molecules.ChatBubble
import com.example.hola_compose_chatapp.ui.organism.ChatItemRow
import com.example.hola_compose_chatapp.ui.organism.CustomAppBar
import com.example.hola_compose_chatapp.ui.organism.CustomHomeBottomBar

@Composable
fun HomeChatList(navController: NavController, homeViewModel: HomeViewModel) {
    val chatList = homeViewModel.chatListView.observeAsState().value!!
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bg_color)),
        topBar = { CustomAppBar() },
        bottomBar = {
            CustomHomeBottomBar(navController)
        },
        floatingActionButton = { CustomFloatingActionButton(navController, homeViewModel) }
    )
    { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        )
        {
            items(chatList)
            { message ->
                ChatItemRow(chatItem = message, viewModel = homeViewModel)
            }


        }
    }
}

@Composable
fun CustomFloatingActionButton(navController: NavController, homeViewModel: HomeViewModel) {
    FloatingActionButton(onClick = { homeViewModel.getChatItemList()/*navController.navigate("search") */ }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "newChat")
    }
}

