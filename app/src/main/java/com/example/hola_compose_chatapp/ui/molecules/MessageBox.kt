package com.example.hola_compose_chatapp.ui.molecules

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hola_compose_chatapp.feature.chat.ChatViewModel
import com.example.hola_compose_chatapp.feature.home.HomeViewModel

@Composable
fun MessageBox(modifier: Modifier,text:MutableState<String>,viewModel: ChatViewModel)
{
    Row(
        modifier
    )
    {
        OutlinedTextField(
            value = text.value,
            onValueChange = {
                text.value = it
            },
            modifier = Modifier
                .padding(10.dp),
            label = { Text(text = "Enter the message", fontSize = 14.sp) },
            shape = RoundedCornerShape(20.dp),
        )
        IconButton( modifier = Modifier
            .padding(20.dp),
            onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
        }
    }
}