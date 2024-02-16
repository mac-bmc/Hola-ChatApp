package com.example.hola_compose_chatapp.ui.organism

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hola_compose_chatapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar()
{
    TopAppBar(title = { Text(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.app_name),
        style = TextStyle(
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif
        )
    ) })
}