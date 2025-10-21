package com.example.myapp.Data

import com.example.myapp.Model.OnboardingSection
import com.example.myapp.R

object OnboardingSectionData {

    fun getMainOnboardingSectionData(): List<OnboardingSection> = listOf(

        OnboardingSection(
            title = "Find Your Next Favorite Dish",
            descriptor = "Explore thousands of curated recipes for any occasion, taste, or skill level. Your next culinary adventure awaits.",
            imageRes = R.drawable.onboarding_image1
        ),
        OnboardingSection(
            title = "Effortless Meal Planning",
            descriptor = "Plan your meals for the week, generate smart shopping lists, and reduce food waste. Eating well has never been easier.",
            imageRes = R.drawable.onboarding_image2
        ),
        OnboardingSection(
            title = "Your Guided Cooking Coach",
            descriptor = "Follow easy step-by-step instructions with built-in timers and tips to make every meal a success, from prep to plate.",
            imageRes = R.drawable.onboarding_image3
        ),

        )

}