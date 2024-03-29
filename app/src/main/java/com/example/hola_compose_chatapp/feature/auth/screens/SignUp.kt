package com.example.hola_compose_chatapp.feature.auth.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hola_compose_chatapp.R
import com.example.hola_compose_chatapp.feature.auth.AuthViewModel
import com.example.hola_compose_chatapp.ui.atoms.CustomProgressIndicator
import com.example.hola_compose_chatapp.ui.atoms.CustomTextField

@Composable
fun SignUp(navController: NavController,authViewModel: AuthViewModel) {
    var email = remember { mutableStateOf("") }
    var password = remember {
        mutableStateOf("")
    }
    var isLoading  = remember {
        mutableStateOf(false)
    }
    // A surface container using the 'background' color from the theme
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bg_color)),
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(isLoading.value){
                CustomProgressIndicator()
                Spacer(modifier = Modifier.height(30.dp))
            }
            Text(
                text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    color = colorResource(id = R.color.white),
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Serif
                )
            )
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = stringResource(id = R.string.signup),
                style = TextStyle(
                    fontSize = 20.sp,

                    )
            )
            Spacer(modifier = Modifier.height(30.dp))
            CustomTextField(hint = stringResource(id = R.string.username), email)
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(hint = stringResource(id = R.string.password), password)
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                              isLoading.value = true
                              authViewModel.signUp(email.value,password.value)
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.height(35.dp)

                ) {
                    Text(
                        text = stringResource(id = R.string.signup),
                        style = TextStyle(color = Color.White)
                    )
                }
                ClickableText(style = TextStyle(color = colorResource(id = R.color.white)),text = AnnotatedString(stringResource(id = R.string.Login)), onClick ={
                    navController.navigate("login")
                } )

            }
        }
    }


}