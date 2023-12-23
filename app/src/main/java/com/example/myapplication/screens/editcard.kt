package com.example.myapplication.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.Display.Mode
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.GlobalUser
import com.example.myapplication.R
import com.example.myapplication.components.ActiveButton
import com.example.myapplication.components.LoginField
import com.example.myapplication.components.PlaceholderInputField
import com.example.myapplication.components.navBar
import com.example.myapplication.components.navButton
import com.example.myapplication.database.MobileAppDataBase
import com.example.myapplication.database.entities.Card
import com.example.myapplication.database.entities.User
import com.example.myapplication.database.viewmodels.CardViewModel
import com.example.myapplication.database.viewmodels.MobileAppViewModelProvider
import com.example.myapplication.database.viewmodels.UserViewModel
import com.example.myapplication.ui.theme.SkyBlue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.log

@Composable
fun EditCardScreen(navController: NavHostController,
                   cardViewModel: CardViewModel = viewModel( factory = MobileAppViewModelProvider.Factory),
                   cardId: Int? = null) {
    val context = LocalContext.current

    val image = remember { mutableStateOf<Bitmap>(BitmapFactory.decodeResource(context.resources, R.drawable.ferrari_laferrari_car2)) }
    val name = remember { mutableStateOf("") }
    val location = remember { mutableStateOf("") }
    val price = remember { mutableStateOf(0) }
    val mileage = remember { mutableStateOf(0) }

    val imageData = remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageData.value = uri
        }
    imageData.value?.let {
        if (Build.VERSION.SDK_INT < 28) {
            image.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, imageData.value)

        } else {
            val source = ImageDecoder
                .createSource(context.contentResolver, imageData.value!!)
            image.value = ImageDecoder.decodeBitmap(source)
        }
    }

    LaunchedEffect(Unit) {
        cardId?.let {
            cardViewModel.getCardById(cardId).collect {
                if (it != null) {
                    image.value = it.image
                }
                if (it != null) {
                    name.value = it.name
                }
                if (it != null) {
                    location.value = it.location
                }
                if (it != null) {
                    mileage.value = it.mileage
                }
                if (it != null) {
                    price.value = it.price
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Bottom,

    ) {
        Image(
            bitmap = image.value.asImageBitmap(),
            contentDescription = "editplaceholder",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(384.dp)
                .padding(8.dp)
                .align(Alignment.CenterHorizontally))
        ActiveButton(label = "Выбрать обложку", backgroundColor = SkyBlue, textColor = Color.Black, onClickAction = {
            launcher.launch("image/*")
        })
        PlaceholderInputField(label = "Название", isSingleLine = true,
            startValue = name.value, onTextChanged = { newName ->
                name.value = newName
            })
        PlaceholderInputField(label = "Расположение", isSingleLine = true,
            startValue = location.value, onTextChanged = { newLocation ->
                location.value = newLocation
            })
        PlaceholderInputField(label = "Пробег", isSingleLine = true,
            startValue = mileage.value.toString(), onTextChanged = { newMileage ->
                mileage.value = newMileage.toIntOrNull() ?: 500
            })
        PlaceholderInputField(label = "Цена", isSingleLine = true,
            startValue = price.value.toString(), onTextChanged = { newPrice ->
                price.value = newPrice.toIntOrNull() ?: 10000
            })
        ActiveButton(label = "Сохранить", backgroundColor = SkyBlue, textColor = Color.Black, onClickAction = {
            cardId?.let {
                cardViewModel.updateCard(
                    Card(
                        id = cardId,
                        image = image.value,
                        name = name.value,
                        location = location.value,
                        mileage = mileage.value,
                        price = price.value,
                        userId = GlobalUser.getInstance().getUser()?.id ?: 1
                    )
                )
            } ?: run {
                cardViewModel.insertCard(
                    Card(
                        image = image.value,
                        name = name.value,
                        location = location.value,
                        mileage = mileage.value,
                        price = price.value,
                        userId = GlobalUser.getInstance().getUser()?.id ?: 1
                    )
                )
            }
            navController.navigate("mainScreen")
        })
        navButton(navController = navController, destination = "mainScreen", label = "Назад", backgroundColor = SkyBlue, textColor = Color.Black)
    }
}

@Composable
fun EditUserScreen(navController: NavHostController,
                   userViewModel: UserViewModel = viewModel(
                       factory = MobileAppViewModelProvider.Factory)){
    val context = LocalContext.current

    var userId = remember { mutableStateOf(0) }
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        GlobalUser.getInstance().getUser()?.let { user ->
            userId.value = user!!.id!!
            login.value = user!!.login
            password.value = user!!.password
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        PlaceholderInputField(label = "Логин", isSingleLine = true,
            startValue = login.value, onTextChanged = { newName ->
                login.value = newName
            })
        PlaceholderInputField(label = "Пароль", isSingleLine = true,
            startValue = password.value, onTextChanged = { newPassword ->
                password.value = newPassword
            })
        ActiveButton(label = "Сохранить", backgroundColor = SkyBlue, textColor = Color.Black, onClickAction = {
            userViewModel.updateUser(
                User(
                    id = userId.value,
                    login = login.value,
                    password = password.value,
                )
            )
            navController.navigate("userSettings")
        })
    }
}