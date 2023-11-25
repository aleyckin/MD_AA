package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.components.navBar
import com.example.myapplication.ui.theme.SkyBlue

@Composable
fun MainScreen(navController: NavHostController) {
    Column {
        Box(modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxHeight(0.9f)) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ListItem(
                    name = "Ламборджини Авентадор 2010",
                    mileage = 4765,
                    location = "Россия, г. Ульяновск",
                    price = 5000000,
                    imageResourceId = com.example.myapplication.R.drawable.lambo_car1,
                    navController = navController
                )
                ListItem(
                    name = "Оранжевый Москвич 1983",
                    mileage = 522011,
                    location = "Россия, г. Москва",
                    price = 125000,
                    imageResourceId = com.example.myapplication.R.drawable.moscowich_car3,
                    navController = navController
                )
                ListItem(
                    name = "Феррари Лаферрари 2013",
                    mileage = 17698,
                    location = "Италия, г. Неаполь",
                    price = 1249990,
                    imageResourceId = com.example.myapplication.R.drawable.ferrari_laferrari_car2,
                    navController = navController
                )
                ListItem(
                    name = "Оранжевый Москвич 1983",
                    mileage = 522011,
                    location = "Россия, г. Москва",
                    price = 125000,
                    imageResourceId = com.example.myapplication.R.drawable.moscowich_car3,
                    navController = navController
                )
                ListItem(
                    name = "Феррари Лаферрари 2013",
                    mileage = 17698,
                    location = "Италия, г. Неаполь",
                    price = 1249990,
                    imageResourceId = com.example.myapplication.R.drawable.ferrari_laferrari_car2,
                    navController = navController
                )
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

@Composable
private fun ListItem(name: String,
                     mileage: Int,
                     location: String,
                     price: Int,
                     imageResourceId: Int,
                     navController: NavHostController) {
    var borderColor = remember { mutableStateOf(Color.Gray) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(2.dp, borderColor.value, RoundedCornerShape(15.dp))
            .clickable { borderColor.value = Color.LightGray },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ){
        Box() {
            Row(verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(id = imageResourceId),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(128.dp))
                Column (
                    modifier = Modifier.padding(5.dp)) {
                    Text(text = name, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.Bold)
                    Text(text = "Пробег: " + mileage)
                    Text(text = "Расположение: " + location)
                    Text(text = "Цена: " + price)
                    Button(onClick = { navController.navigate("editCard") }) {
                        Text("Изменить")
                    }
                    Button(onClick = { }) {
                        Text("Удалить")
                    }
                }
            }

        }
    }
}