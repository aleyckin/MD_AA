package com.example.myapplication.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val buttonHeightStandard = 72.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceholderInputField(label: String, startValue: String? = null, isSingleLine: Boolean, onTextChanged: (String) -> Unit){
    var text = remember { mutableStateOf("") }
    startValue?.let{
        text.value = startValue
    }
    Text(
        modifier = Modifier.padding(horizontal = 8.dp),
        text = label,
        fontSize = 20.sp
    )
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it
            onTextChanged(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, bottom = 8.dp, end = 16.dp),
        shape = RoundedCornerShape(10.dp),
        singleLine = isSingleLine
    )
}

@Composable
fun ActiveButton(label: String, backgroundColor: Color, textColor: Color, onClickAction: () -> Unit){
    Button(
        onClick = onClickAction,
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(buttonHeightStandard)
            .padding(top = 8.dp, start = 16.dp, bottom = 8.dp, end = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}