package com.example.myapp.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.myapp.Data.OnboardingSectionData
import com.example.myapp.Model.OnboardingSection

@Composable
fun OnboardingSection (OnboardingSection: OnboardingSection){
    Column (modifier = Modifier.fillMaxWidth()){
        Spacer(modifier = Modifier.size(90.dp))
        Image(painter = painterResource(id = OnboardingSection.imageRes),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 0.dp),
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.size(80.dp))
        Text(
            text = OnboardingSection.title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(60.dp))
        Text(
            text = OnboardingSection.descriptor,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 0.dp)
        )

        Spacer(modifier = Modifier.size(50.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingSectionPreview1(){
    OnboardingSection(OnboardingSectionData.getMainOnboardingSectionData()[0])
}
@Preview(showBackground = true)
@Composable
fun OnboardingSectionPreview2(){
    OnboardingSection(OnboardingSectionData.getMainOnboardingSectionData()[1])
}
@Preview(showBackground = true)
@Composable
fun OnboardingSectionPreview3(){
    OnboardingSection(OnboardingSectionData.getMainOnboardingSectionData()[2])
}