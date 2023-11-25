package com.example.myapplication.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginField(text: String)
{
    var message = remember{ mutableStateOf("")}

    OutlinedTextField(
        value = message.value,
        onValueChange = { message.value = it },
        placeholder = { Text(text) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 50.dp, end = 50.dp),
        shape = RoundedCornerShape(25.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(text: String)
{
    var message = remember{ mutableStateOf("")}

    OutlinedTextField(
        value = message.value,
        onValueChange = { message.value = it },
        placeholder = { Text(text) },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 50.dp, end = 50.dp),
        shape = RoundedCornerShape(25.dp))
}