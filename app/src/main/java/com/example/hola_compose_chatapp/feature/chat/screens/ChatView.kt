package com.example.hola_compose_chatapp.feature.chat.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.hola_compose_chatapp.R
import com.example.hola_compose_chatapp.feature.chat.ChatViewModel
import com.example.hola_compose_chatapp.model.MessageModel
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.ui.molecules.ChatBubble
import com.example.hola_compose_chatapp.ui.molecules.MessageBox

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ChatView(chatViewModel: ChatViewModel) {
    val text = remember {
        mutableStateOf("")
    }
    val messageList = listOf<MessageModel>(
        MessageModel(
            "",
            "Hello",
            "10.00 AM",
            0,
            UserModel("", "Manu", ""),
            false,
            UserModel("", "Manu", ""),
            false
        ),
        MessageModel(
            "",
            "Hello",
            "10.00 AM",
            0,
            UserModel("", "Manu", ""),
            false,
            UserModel("", "Manu", ""),
            false
        ),
        MessageModel(
            "",
            "Hello",
            "10.00 AM",
            0,
            UserModel("", "Manu", ""),
            false,
            UserModel("", "Manu", ""),
            false
        ),
        MessageModel(
            "",
            "Hello",
            "10.00 AM",
            0,
            UserModel("", "Manu", ""),
            true,
            UserModel("", "Manu", ""),
            false
        ),
        MessageModel(
            "",
            "Hello",
            "10.00 AM",
            0,
            UserModel("", "Manu", ""),
            true,
            UserModel("", "Manu", ""),
            false
        ),
        MessageModel(
            "",
            "Hello",
            "10.00 AM",
            0,
            UserModel("", "Manu", ""),
            false,
            UserModel("", "Manu", ""),
            false
        ),

    )
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.bg_color)),
                navigationIcon = {
                    IconButton(onClick = {
                        chatViewModel.openHomeList()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                },
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.placeholder_image),
                            //painter = painter,
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = "Manu", modifier = Modifier.padding(5.dp),
                            style = TextStyle(
                                color = colorResource(id = R.color.white),
                                fontSize = 20.sp
                            )
                        )

                    }
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
            val (messages, chatBox) = createRefs()
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(10.dp)
                    .constrainAs(messages) {
                        top.linkTo(parent.top)
                        bottom.linkTo(chatBox.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    },
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {
                items(messageList)
                { message ->
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = if (message.isSendByMe) Arrangement.End else Arrangement.Start
                    ) {
                        ChatBubble(messageModel = message)
                    }
                }


            }
            MessageBox(modifier = Modifier
                .constrainAs(chatBox) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(), text = text,chatViewModel)

        }

    }


}
