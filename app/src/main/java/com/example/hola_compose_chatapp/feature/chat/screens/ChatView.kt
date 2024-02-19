package com.example.hola_compose_chatapp.feature.chat.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.hola_compose_chatapp.R
import com.example.hola_compose_chatapp.feature.chat.ChatViewModel
import com.example.hola_compose_chatapp.feature.home.HomeViewModel
import com.example.hola_compose_chatapp.model.ChatItemModel
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.ui.atoms.CustomProgressIndicator
import com.example.hola_compose_chatapp.ui.molecules.ChatBubble
import com.example.hola_compose_chatapp.ui.organism.ChatItemRow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ChatView(chatViewModel: ChatViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        chatViewModel.openHomeList()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                },
                title = {
                    Text(
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
                    )
                })
        },
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bg_color)),
    )
    { innerPadding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
           val (messages,chatBox) = createRefs()
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .constrainAs(messages) {
                        top.linkTo(parent.top)
                        bottom.linkTo(chatBox.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    },
                horizontalAlignment = Alignment.Start
            )
            {
                item {
                    ChatBubble(text = "hi")
                    ChatBubble(text = "hello")
                }

            }
            /*MessageSent(
                Modifier
                    .constrainAs(chatBox) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth())*/

        }

    }


}