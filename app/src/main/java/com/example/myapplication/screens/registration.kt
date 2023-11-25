package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.components.LoginField
import com.example.myapplication.components.PasswordField
import com.example.myapplication.components.navButton

@Composable
fun Registration(navController: NavHostController) {
    Column (modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Привет!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        Text(text = "Зарегистрируйся, чтобы начать!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        LoginField(text = "Логин")
        PasswordField(text = "Пароль")
        PasswordField(text = "Повторите пароль")
        navButton(navController = navController, destination = "authorization", label = "Зарегистрироваться", backgroundColor = Color.Cyan, textColor = Color.Black)
    }
}