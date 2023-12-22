package com.example.myapplication.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.components.navBar
import com.example.myapplication.database.entities.Card
import com.example.myapplication.ui.theme.SkyBlue
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.database.MobileAppDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val cards = remember { mutableStateListOf<Card>() }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val database = MobileAppDataBase.getInstance(context)
            database.cardDao().getAll().collect { data ->
                cards.clear()
                data.forEach { cards.add(it) }
            }
        }
    }
    
    Column {
        Box(modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxHeight(0.9f)) {
            Column()
            {
                DataListScroll(navController, cards)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(SkyBlue)
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            navBar(navController = navController)
        }
    }
}

@Composable
fun <T : Any> DataListScroll(navController: NavHostController, dataList: List<T>){
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ){
        item {
            when {
                dataList.isListOf<Card>() -> addNewListItem(navController, "editcard")

            }
        }
        items(dataList){ item ->
            when(item){
                is Card -> CardListItem(item = item, navController = navController)
            }
        }
    }
}

inline fun <reified T> List<*>.isListOf(): Boolean {
    return isNotEmpty() && all { it is T }
}


@Composable
fun CardListItem(item: Card, navController: NavHostController){
    val context = LocalContext.current

    val showDialog = remember {
        mutableStateOf(false)
    }

    val delete = remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(2.dp, Color.Gray, RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ){
                Image(bitmap = item.image.asImageBitmap(),
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(128.dp)
                        .height(256.dp))
                Column (
                    modifier = Modifier.padding(8.dp)
                ){
                    Text(
                        text = "Название: ${item.name} \nРасположение: ${item.location} \nПробег: ${item.mileage} \nЦена: ${item.price}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    DataListItemButton("Изменить", Color.LightGray, Color.White, onClickAction = {
                        navController.navigate("editcard/${item.id}")
                    })
                    DataListItemButton("Удалить", Color.Black, Color.White, onClickAction = {
                        showDialog.value = !showDialog.value
                    })
                }
            }
        }
    }

    if(showDialog.value) {
        DialogWindow(label = "Подтверждение",
            message = "Вы уверены что хотите удалить запись?", onConfirmAction = {
                delete.value = !delete.value
                showDialog.value = !showDialog.value
            }, onDismissAction = {
                showDialog.value = !showDialog.value
            })
    }

    if(delete.value) {
        LaunchedEffect(Unit){
            withContext(Dispatchers.IO){
                MobileAppDataBase.getInstance(context).cardDao().delete(item)
            }
        }
        delete.value = !delete.value
        navController.navigate("mainScreen")
    }
}


@Composable
fun DataListItemButton(label: String, backgroundColor: Color, textColor: Color, onClickAction: () -> Unit){
    Button(
        onClick = onClickAction,
        modifier = Modifier.requiredHeight(64.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        )
    ) {
        Text(
            text = label,
            color = textColor,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun DialogWindow(label: String, message: String, onConfirmAction: () -> Unit, onDismissAction: () -> Unit){
    AlertDialog(onDismissRequest = onDismissAction,
        title = {
            Text(text = label,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            Button(
                onClick = onConfirmAction,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = "Подтвердить",
                    color = Color.White)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismissAction,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = "Отмена",
                    color = Color.Black)
            }
        }
    )
}

@Composable
fun addNewListItem(navController: NavHostController, destination: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, top = 8.dp, end = 18.dp, bottom = 8.dp)
            .clickable {
                navController.navigate(destination)
            },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(painter = painterResource(id = R.drawable.plus),
                    contentDescription = "additem",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(96.dp)
                        .padding(8.dp))
                Text(
                    text = "Добавить",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 32.dp))
            }
        }
    }
}

