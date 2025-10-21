package com.example.myapp.ui.onboarding

import android.content.Context

class OnboardingUtil(private val context: Context) {
    fun isOnboardingCompleted() : Boolean {
        val sharedPreferences = context.getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("completed", false)
    }

    fun setOnboardingCompleted() {
        val sharedPreferences = context.getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("completed", true).apply()
    }
}