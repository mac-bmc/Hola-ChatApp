package com.example.hola_compose_chatapp.ui.organism

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hola_compose_chatapp.R

@Composable
fun CustomHomeBottomBar(navController: NavController)
{
    BottomAppBar(
        containerColor = colorResource(id = R.color.bg_color),
        contentColor = colorResource(id = R.color.white),
        modifier = Modifier.height(60.dp),
    ) {
        IconButton(onClick = { /*TODO*/ }, Modifier.weight(1f)) {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "Archive")
        }
        IconButton(onClick = { navController.navigate("home") }, Modifier.weight(1f)) {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Archive")
        }
        IconButton(onClick = { navController.navigate("profile") }, Modifier.weight(1f)) {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Archive")
        }
    }
}