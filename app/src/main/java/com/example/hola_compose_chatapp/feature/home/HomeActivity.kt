package com.example.hola_compose_chatapp.feature.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.hola_compose_chatapp.R
import com.example.hola_compose_chatapp.feature.chat.ChatActivity
import com.example.hola_compose_chatapp.feature.home.ui.theme.HolaComposeChatAppTheme
import com.example.hola_compose_chatapp.navigation.HomeNavHost
import com.example.hola_compose_chatapp.utils.Either
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private var syncRunnable: Runnable? = null
    val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        getData()
        attachObservers()
        setContent {
            HolaComposeChatAppTheme {
                HomeNavHost(homeViewModel)
            }
        }
    }

    private fun getData() {
        homeViewModel.getChatItemList()
    }

    private fun attachObservers() {
        observeChatOpen()
        observeUserSync()
        observeMessageSync()
    }

    private fun observeMessageSync() {
        homeViewModel.messageSyncStatus.observe(this) { syncStatus ->
            when (syncStatus) {
                is Either.Failed -> {
                    Toast.makeText(this, getString(R.string.error_sync_msg), Toast.LENGTH_LONG)
                        .show()
                }

                is Either.Success -> {

                }
            }

        }
    }

    private fun observeUserSync() {
        homeViewModel.userSyncStatus.observe(this) { syncStatus ->
            when (syncStatus) {
                is Either.Failed -> {
                    Toast.makeText(this, getString(R.string.error_sync_msg), Toast.LENGTH_LONG)
                        .show()
                }

                is Either.Success -> {

                }
            }

        }
    }

    private fun observeChatOpen() {
        homeViewModel.chatViewOpen.observe(this) { shouldOpen ->
            when (shouldOpen) {
                true -> {
                    startActivity(Intent(this@HomeActivity, ChatActivity::class.java))
                    Log.d("observeChatOpen", "called")
                }

                false -> {}
            }

        }
    }

    override fun onRestart() {
        super.onRestart()
        homeViewModel.resetOpenState()
        Log.d("resumed state value", homeViewModel.chatViewOpen.value.toString())
    }

    /*override fun onResume() {
        super.onResume()
        startDataSync()
    }

    override fun onPause() {
        super.onPause()
        pauseDataSync()
    }*/

    private fun pauseDataSync() {
        syncRunnable?.let {
            handler.removeCallbacks(it)
        }
    }

    private fun startDataSync() {
        syncRunnable = object : Runnable {
            override fun run() {
                try {
                    homeViewModel.syncMessages()
                    homeViewModel.syncHolaUsers()
                } catch (e: Exception) {
                    Log.d("TIMER", e.toString())
                } finally {
                    handler.postDelayed(this, 30000L)
                }
            }

        }
        handler.postDelayed(syncRunnable!!, 30000L)
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


