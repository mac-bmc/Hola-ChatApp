package com.example.hola_compose_chatapp.ui.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(hint: String, text: MutableState<String>) {
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp),
        label = { Text(text = hint, fontSize = 14.sp) },
        shape = RoundedCornerShape(20.dp),
    )
}