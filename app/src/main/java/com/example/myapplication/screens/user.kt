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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.components.LoginField
import com.example.myapplication.components.navBar
import com.example.myapplication.database.MobileAppDataBase
import com.example.myapplication.database.entities.User
import com.example.myapplication.ui.theme.SkyBlue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun UserSettings(navController: NavHostController) {
    val context = LocalContext.current

    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val adsCount = remember { mutableStateOf(0) }
    val userId = 1

    userId?.let {
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                val user = MobileAppDataBase.getInstance(context).userDao().getById(userId!!)
                login.value = user!!.login
                password.value = user!!.password
                adsCount.value = MobileAppDataBase.getInstance(context).userDao().getAdsCount(userId)?: 0
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
                LoginField(text = "Логин пользователя: ${login.value}")
                LoginField(text = "Пароль пользователя: ${password.value}")
                LoginField(text = "Количество объявлений пользователя: ${adsCount.value}")
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