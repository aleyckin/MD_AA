package com.example.myapplication.screens

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.components.ActiveButton
import com.example.myapplication.components.LoginField
import com.example.myapplication.components.PasswordField
import com.example.myapplication.components.PasswordInputField
import com.example.myapplication.components.PlaceholderInputField
import com.example.myapplication.components.PlaceholderInputFieldAuth
import com.example.myapplication.components.navButton
import com.example.myapplication.database.MobileAppDataBase
import com.example.myapplication.database.entities.Card
import com.example.myapplication.database.entities.User
import com.example.myapplication.database.viewmodels.MobileAppViewModelProvider
import com.example.myapplication.database.viewmodels.UserViewModel
import com.example.myapplication.ui.theme.SkyBlue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun Authorization(navController: NavHostController,
                  userViewModel: UserViewModel = viewModel( factory = MobileAppViewModelProvider.Factory )){
    val context = LocalContext.current
    val users = userViewModel.getAllUsers.collectAsState(emptyList()).value
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

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
        PlaceholderInputFieldAuth(label = "Логин", isSingleLine = true, onTextChanged = { newlogin ->
            login.value = newlogin
        })
        PasswordInputField(label = "Пароль", onPasswordChanged = { newpassword ->
            password.value = newpassword
        })

        ActiveButton(label = "Вход", backgroundColor = SkyBlue,
            textColor = Color.Black, onClickAction = {
                if (login.value.isNotEmpty() && password.value.isNotEmpty()) {
                    userViewModel.authUser(
                        User(
                            login = login.value,
                            password = password.value,
                        )
                    )
                    navController.navigate("mainScreen")
                }
            })
        navButton(navController = navController, destination = "registration", label = "Регистрация",
            backgroundColor = SkyBlue, textColor = Color.Black)
    }
}