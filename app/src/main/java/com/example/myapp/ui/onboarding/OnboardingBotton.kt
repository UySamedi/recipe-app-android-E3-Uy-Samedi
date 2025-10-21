package com.example.myapp.ui.onboarding

import androidx.annotation.Size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingBotton(
    text: String = "Next",
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    fontSize: Int = 14,
    onClick: () -> Unit
){
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(
        containerColor = backgroundColor,contentColor = textColor),
        shape = RoundedCornerShape(10.dp),
    )
    {
        Text(text = text, style = textStyle, fontSize = fontSize.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun NextButton(){
    OnboardingBotton(text = "Next")
    {

    }
}

@Preview(showBackground = true)
@Composable
fun BackButton(){
    OnboardingBotton(text = "Back",
        backgroundColor = Color.Transparent,
        textColor = Color.Gray,
        textStyle = MaterialTheme.typography.bodySmall,
        fontSize = 13
    ){}
}