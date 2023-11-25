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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.components.LoginField
import com.example.myapplication.components.navBar
import com.example.myapplication.ui.theme.SkyBlue

@Composable
fun UserSettings(navController: NavHostController) {
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
                LoginField(text = "Логин пользователя")
                LoginField(text = "Пароль пользователя")
                LoginField(text = "Количество объявлений пользователя")
                Button(onClick = {}) { Text("Посмотреть мои объявления") }
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