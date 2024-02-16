package com.example.hola_compose_chatapp.feature.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.hola_compose_chatapp.feature.home.HomeActivity
import com.example.hola_compose_chatapp.navigation.AuthNavHost
import com.example.hola_compose_chatapp.ui.theme.HolaComposeChatAppTheme
import com.example.hola_compose_chatapp.utils.Either
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        attachObservers()
        setContent {
            HolaComposeChatAppTheme {
                AuthNavHost(authViewModel)
            }
        }
    }

    private fun attachObservers() {
        attachAuthSuccessObserver()
    }

    private fun attachAuthSuccessObserver() {
        authViewModel.signUpState.observe(this@AuthActivity)
        { signUpState ->
            when(signUpState)
            {
                is Either.Success -> {
                    startActivity(Intent(this@AuthActivity,HomeActivity::class.java))
                }
                is Either.Failed -> {
                    Toast.makeText(this,signUpState.msg,Toast.LENGTH_LONG).show()
                }
                else ->{}
            }

        }
        authViewModel.loginState.observe(this@AuthActivity)
        { loginState ->
            when(loginState)
            {
                is Either.Success -> {
                    startActivity(Intent(this@AuthActivity,HomeActivity::class.java))
                }
                is Either.Failed -> {
                    Toast.makeText(this,loginState.msg,Toast.LENGTH_LONG).show()
                }
                else ->{}
            }

        }
        authViewModel.isLoggedIn()
        authViewModel.isLoggedIn.observe(this){isLoggedIn->
            if(isLoggedIn)
            {
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
        }
    }

}





