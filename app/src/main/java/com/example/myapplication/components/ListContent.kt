/*
package com.example.myapplication.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun DataListScroll(navController: NavHostController){
    val isFirstTime = remember { mutableStateOf(true) }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ){
        item {
            addNewListItem(navController, "editstory")
        }
        items(storySingleton.getStoryList()){ item ->
            DataListItem(item = item, navController = navController)
        }
    }
}

@Composable
fun DataListItem(item: Story, navController: NavHostController){
    val isExpanded = remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, top = 10.dp, end = 18.dp, bottom = 10.dp)
            .clickable {
                isExpanded.value = !isExpanded.value
            },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = ButtonColor1
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ){
                Image(painter = painterResource(id = item.cover),
                    contentDescription = item.description,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(128.dp)
                        .height(256.dp))
                Column (
                    modifier = Modifier.padding(8.dp)
                ){
                    Text(
                        text = item.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = item.description)
                }
            }
            AnimatedVisibility(
                visible = isExpanded.value,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    DataListItemButton("Изменить", ButtonColor2, Color.White, onClickAction = { navController.navigate("editstory") })
                    DataListItemButton("Удалить", Color.Red, Color.White, onClickAction = { })
                }
            }
        }
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
*/
