package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.components.LoginField
import com.example.myapplication.components.PasswordField
import com.example.myapplication.components.navButton

@Composable
fun Authorization(navController: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp),
        verticalArrangement =  Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.intro3),
                contentDescription = "login",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(400.dp))
        Box (modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            contentAlignment = Alignment.Center) {
            Text("Продажа автомобилей",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        LoginField(text = "Логин")
        PasswordField(text = "Пароль")
        navButton(navController = navController, destination = "mainScreen", label = "Вход",
            backgroundColor = Color.Cyan, textColor = Color.Black)
        navButton(navController = navController, destination = "registration", label = "Регистрация",
            backgroundColor = Color.Cyan, textColor = Color.Black)
    }
}