package com.example.hola_compose_chatapp.ui.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hola_compose_chatapp.R
import com.example.hola_compose_chatapp.model.MessageModel

@Composable
fun ChatBubble(messageModel: MessageModel) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.bg_color))
    ) {
        Column(modifier = Modifier.align(Alignment.TopStart)) {
            Text(
                text = messageModel.sendUserId, modifier = Modifier.padding(5.dp),
                style = TextStyle(color = colorResource(id = R.color.white), fontSize = 18.sp)
            )
            Row {
                Text(
                    text = messageModel.content, modifier = Modifier.padding(10.dp),
                    style = TextStyle(fontSize = 22.sp)
                )
                Text(
                    text = messageModel.sentTime, modifier = Modifier.padding(10.dp),
                    style = TextStyle(color = colorResource(id = R.color.white), fontSize = 14.sp)
                )
            }
        }
    }
}
