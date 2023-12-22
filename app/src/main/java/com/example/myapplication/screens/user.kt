package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.GlobalUser
import com.example.myapplication.MobileApp
import com.example.myapplication.R
import com.example.myapplication.components.ActiveButton
import com.example.myapplication.components.LoginField
import com.example.myapplication.components.PlaceholderInputField
import com.example.myapplication.components.navBar
import com.example.myapplication.database.MobileAppDataBase
import com.example.myapplication.database.entities.Card
import com.example.myapplication.database.entities.User
import com.example.myapplication.database.viewmodels.MobileAppViewModelProvider
import com.example.myapplication.database.viewmodels.UserViewModel
import com.example.myapplication.ui.theme.SkyBlue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun UserSettings(navController: NavHostController,
                 userViewModel: UserViewModel = viewModel(factory = MobileAppViewModelProvider.Factory)) {
    val context = LocalContext.current
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val adsCount = remember { mutableStateOf(0) }
    val userId = GlobalUser.getInstance().getUser()?.id!!

    LaunchedEffect(Unit) {
        userId?.let {
            userViewModel.getUser(userId).collect {
                if (it != null) {
                    login.value = it.login
                }
                if (it != null) {
                    password.value = it.password
                }
            }
        }
    }

    Column {
        Box (modifier = Modifier.fillMaxHeight(0.9f)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "login",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(300.dp)
                )
                PlaceholderInputField(label = "Логин пользователя: ${login.value}", isSingleLine = true, onTextChanged = {newlogin ->
                    login.value = newlogin})
                PlaceholderInputField(label = "Пароль пользователя: ${password.value}", isSingleLine = true, onTextChanged = {newpassword ->
                    password.value = newpassword})
                ActiveButton(label = "Сохранить изменения", backgroundColor = SkyBlue, textColor = Color.Black, onClickAction =
                {
                    userViewModel.updateUser(User(
                        login = login.value,
                        password = password.value
                    ))
                })
            }
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .background(SkyBlue),
            verticalArrangement = Arrangement.Center) {
            navBar(navController = navController)
        }
    }
}