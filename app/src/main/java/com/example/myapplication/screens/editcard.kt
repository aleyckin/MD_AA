package com.example.myapplication.screens

import android.view.Display.Mode
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.components.LoginField
import com.example.myapplication.components.navBar
import com.example.myapplication.components.navButton
import com.example.myapplication.ui.theme.SkyBlue

@Composable
fun EditCard(navController: NavHostController) {
    Column {
        Box (modifier = Modifier.fillMaxHeight(0.9f)) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text("Добавить картинку")
                }
                LoginField(text = "Название/Марка")
                LoginField(text = "Пробег")
                LoginField(text = "Расположение")
                LoginField(text = "Цена")
                Button(onClick = { /*TODO*/ }) {
                    Text("Изменить объявление")
                }
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