package com.example.myapp.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapp.Model.OnboardingSection
import com.example.myapp.ui.theme.MyappTheme

@Composable
fun OnboardingIndicator(
    pageSize: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
){
    Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier){
        repeat(pageSize){
            Spacer(modifier = Modifier.size(2.5.dp))
            Box(modifier = Modifier.height(14.dp)
                .width(width = if(it == currentPage) 32.dp else 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    color = if(it == currentPage) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.secondaryContainer
                ))
            Spacer(modifier = Modifier.size(2.5.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IndicatorPreview1(){
    MyappTheme {
        OnboardingIndicator(
            pageSize = 3,
            currentPage = 0
        )
    }

}

@Preview(showBackground = true)
@Composable
fun IndicatorPreview2(){
    OnboardingIndicator(pageSize = 3, currentPage = 1)
}

@Preview(showBackground = true)
@Composable
fun IndicatorPreview3(){
    OnboardingIndicator(pageSize = 3, currentPage = 2)
}