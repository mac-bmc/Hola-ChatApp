package com.example.hola_compose_chatapp.ui.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.hola_compose_chatapp.R

@Composable
fun ChatBubble(text:String) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.bg_color))
    ) {
        Text(text = text, modifier = Modifier.padding(20.dp))
    }
}