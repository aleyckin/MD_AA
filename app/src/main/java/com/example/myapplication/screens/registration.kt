package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.GlobalUser
import com.example.myapplication.components.ActiveButton
import com.example.myapplication.components.LoginField
import com.example.myapplication.components.PasswordField
import com.example.myapplication.components.PasswordInputField
import com.example.myapplication.components.PlaceholderInputField
import com.example.myapplication.components.PlaceholderInputFieldAuth
import com.example.myapplication.components.navButton
import com.example.myapplication.database.entities.User
import com.example.myapplication.database.viewmodels.MobileAppViewModelProvider
import com.example.myapplication.database.viewmodels.UserViewModel
import com.example.myapplication.ui.theme.SkyBlue

@Composable
fun Registration(navController: NavHostController,
                 userViewModel: UserViewModel = viewModel(
                    factory = MobileAppViewModelProvider.Factory)) {
    val isRegistrated = remember { mutableStateOf(false) }

    if(GlobalUser.getInstance().getUser() != null && !isRegistrated.value) {
        isRegistrated.value = !isRegistrated.value
        navController.navigate("mainScreen")
    }

    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val repeatepassword = remember { mutableStateOf("") }

    Column (modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        PlaceholderInputFieldAuth(label = "Логин", isSingleLine = true, onTextChanged = {newlogin ->
            login.value = newlogin
        })
        PasswordInputField(label = "Пароль", onPasswordChanged = {newpassword ->
            password.value = newpassword
        })
        PasswordInputField(label = "Повторите пароль", onPasswordChanged = {newpassword ->
            repeatepassword.value = newpassword
        })
        ActiveButton(label = "Зарегистрироваться", backgroundColor = SkyBlue,
            textColor = Color.Black, onClickAction = {
                if (password.value == repeatepassword.value){
                    userViewModel.regUser(
                        User(
                            login = login.value,
                            password = password.value,
                        )
                    )
                }
            })
        navButton(navController = navController, destination = "authorization",
            label = "Назад", backgroundColor = SkyBlue, textColor = Color.Black)
    }
}