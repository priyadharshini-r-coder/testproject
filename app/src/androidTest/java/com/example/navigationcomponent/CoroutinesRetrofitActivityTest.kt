package com.example.navigationcomponent

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import android.R

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click

import androidx.test.espresso.matcher.ViewMatchers.withId

import org.junit.Before

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.navigationcomponent.activity.CoroutinesRetrofitActivity
import com.example.navigationcomponent.viewmodel.CoroutinesRetrofitViewModel

import org.junit.Rule
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class CoroutinesRetrofitActivityTest {
    @Rule
    var activityRule = ActivityScenarioRule(
     CoroutinesRetrofitActivity::class.java
    )
    var viewModelTest:CoroutinesRetrofitViewModel= CoroutinesRetrofitViewModel()

    @Before
    fun initValidString() {
        // Specify a valid string.

    }

    @Test
    fun response() {
        // Type text and then press the button.

        onView(withId(R.id.button1)).perform(click())
        viewModelTest.getCountry()

        // Check that the text was changed.

    }
}