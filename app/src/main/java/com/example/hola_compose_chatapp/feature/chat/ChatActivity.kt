package com.example.hola_compose_chatapp.feature.chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.hola_compose_chatapp.feature.chat.screens.ChatView
import com.example.hola_compose_chatapp.feature.chat.ui.theme.HolaComposeChatAppTheme

class ChatActivity : ComponentActivity() {
    private lateinit var chatViewModel: ChatViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
        attachObservers()

        super.onCreate(savedInstanceState)
        setContent {
            HolaComposeChatAppTheme {
                ChatView(chatViewModel)
            }
        }
    }

    private fun attachObservers() {
        openHomeObserver()
    }

    private fun openHomeObserver() {
        chatViewModel.isOpenHomeList.observe(this)
        {openHome->
            when(openHome)
            {
                true->{
                    finish()
                }
                false->{}
            }
        }
    }
}


