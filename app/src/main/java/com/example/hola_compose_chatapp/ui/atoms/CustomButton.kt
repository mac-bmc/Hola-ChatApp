package com.example.hola_compose_chatapp.ui.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

@Composable
fun CustomButton(btnText:String,viewModel: ViewModel)
{
    Button(
        onClick = {


        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.height(35.dp)

    ) {
        Text(text = btnText, style = TextStyle(color = Color.White))
    }
}