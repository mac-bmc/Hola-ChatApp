package com.example.hola_compose_chatapp.feature.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
        attachObservers()
        homeViewModel.syncEzKartMapping()
        setContent {
            HolaComposeChatAppTheme {
                HomeNavHost(homeViewModel)
            }
        }
    }


    private fun attachObservers() {
        observeChatOpen()
        observeExecMapping()
        observeMessageSync()
    }

    private fun observeExecMapping() {
        homeViewModel.mapSyncStatus.observe(this) { mapSync ->
            Log.d("SyncMap","SyncMapToSyncMessageObs")
            when (mapSync) {
                true -> {
                    Log.d("SyncMap","SyncMapToSyncMessage")
                    homeViewModel.syncMessages()
                }

                false -> {
                    Toast.makeText(this, getString(R.string.error_sync_msg), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun observeMessageSync() {
        homeViewModel.messageSyncStatus.observe(this) { syncStatus ->
            Log.d("SyncMMMM","messageSync")
            when (syncStatus) {
                is Either.Failed -> {
                    Toast.makeText(this, getString(R.string.error_sync_msg), Toast.LENGTH_LONG)
                        .show()
                }

                is Either.Success -> {
                    Log.d("ChatMessage","act")
                    homeViewModel.getChatItem()

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

    /*private fun pauseDataSync() {
        syncRunnable?.let {
            handler.removeCallbacks(it)
        }
    }

    private fun startDataSync() {
        syncRunnable = object : Runnable {
            override fun run() {
                try {
                    homeViewModel.syncMessages()
                } catch (e: Exception) {
                    Log.d("TIMER", e.toString())
                } finally {
                    handler.postDelayed(this, 30000L)
                }
            }

        }
        handler.postDelayed(syncRunnable!!, 30000L)
    }*/
}




