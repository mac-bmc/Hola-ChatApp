package com.example.hola_compose_chatapp.feature.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.hola_compose_chatapp.R
import com.example.hola_compose_chatapp.feature.home.HomeViewModel
import com.example.hola_compose_chatapp.model.ChatItemModel
import com.example.hola_compose_chatapp.model.UserModel
import com.example.hola_compose_chatapp.ui.molecules.SearchView
import com.example.hola_compose_chatapp.ui.organism.ChatItemRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearch(navController: NavController, homeViewModel: HomeViewModel) {
    val searchText = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val userResult = listOf<ChatItemModel>(
        ChatItemModel(
            UserModel("", "Mac", ""), listOf(), "Hi How r u", "10.00AM"
        ),
        ChatItemModel(
            UserModel("", "Mac", ""), listOf(), "Hi How r u", "10.00AM"
        ), ChatItemModel(
            UserModel("", "Mac", ""), listOf(), "Hi How r u", "10.00AM"
        )
    )
    Scaffold(
        containerColor = colorResource(id = R.color.bg_color),
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()

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
                }
            )
        },
    )
    { innerPadding ->
        ConstraintLayout(
            Modifier
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            val (searchBox, searchResults) = createRefs()
            SearchView(
                state = searchText,
                modifier = Modifier.constrainAs(searchBox) {
                    top.linkTo(parent.top)
                    bottom.linkTo(searchResults.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            )
            LazyColumn(
                modifier = Modifier
                    .constrainAs(searchResults) {
                        top.linkTo(searchBox.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {
                items(userResult)
                { user ->
                    ChatItemRow(chatItem = user, viewModel = homeViewModel)

                }
            }

        }
    }
}