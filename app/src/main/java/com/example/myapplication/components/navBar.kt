package com.example.myapplication.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.SkyBlue

@Composable
fun navBar(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        navItem(navController = navController, ImageId = R.drawable.home_image, "mainScreen")
        navItem(navController = navController, ImageId = R.drawable.note_image, "editcard")
        navItem(navController = navController, ImageId = R.drawable.profile_image, "userSettings")
    }
}

@Composable
fun navItem(navController: NavHostController, ImageId: Int, destination: String) {
    Image(
        painter = painterResource(id = ImageId),
        contentDescription = "image",
        modifier = Modifier
            .height(45.dp)
            .width(45.dp)
            .clickable
            { navController.navigate(destination) })
}

@Composable
fun navButton(navController: NavHostController, destination: String, label: String, backgroundColor: Color, textColor: Color)
{
    Button(onClick = { navController.navigate(destination) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 50.dp, end = 50.dp),
        colors = ButtonDefaults.buttonColors( containerColor = backgroundColor),
        shape = RoundedCornerShape(25.dp)
        ) {
        Text(text = label,
            fontSize = 20.sp,
            color = textColor
        )
    }
}
