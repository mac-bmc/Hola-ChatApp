package com.example.hola_compose_chatapp.feature.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.hola_compose_chatapp.feature.chat.ChatActivity
import com.example.hola_compose_chatapp.feature.home.ui.theme.HolaComposeChatAppTheme
import com.example.hola_compose_chatapp.navigation.HomeNavHost

class HomeActivity : ComponentActivity() {
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        attachObservers()
        setContent {
            HolaComposeChatAppTheme {
                // A surface container using the 'background' color from the theme
                HomeNavHost(homeViewModel)
            }
        }
    }

    private fun attachObservers() {
        observeChatOpen()
    }

    private fun observeChatOpen() {
        homeViewModel.chatViewOpen.observe(this) { shouldOpen ->
            when (shouldOpen) {
                true -> {
                    startActivity(Intent(this@HomeActivity, ChatActivity::class.java))
                    Log.d("observechatopne","called")
                }

                false -> {}
            }

        }
    }

    override fun onRestart() {
        super.onRestart()
        homeViewModel.resetOpenState()
        Log.d("resumed state value",homeViewModel.chatViewOpen.value.toString())
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HolaComposeChatAppTheme {
        Greeting("Android")
    }
}