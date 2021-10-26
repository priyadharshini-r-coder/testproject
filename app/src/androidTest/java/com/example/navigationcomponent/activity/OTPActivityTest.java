package com.example.navigationcomponent.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.mvp.omnicure.R;

import org.junit.Rule;
import org.junit.Test;

public class OTPActivityTest {
    @Rule
    public ActivityScenarioRule<OTPActivity> activityRule
            = new ActivityScenarioRule<>(OTPActivity.class);

    @Test
    public void checkDisplayed() {
        onView(withId(R.id.otpMsg)).check(matches(isDisplayed()));

        onView(withId(R.id.id_change_number_txt)).check(matches(isDisplayed()));
        onView(withId(R.id.pin_entry_border)).check(matches(isDisplayed()));



        onView(withId(R.id.id_resend_text)).check(matches(isDisplayed()));


        onView(withId(R.id.id_back_button)).check(matches(isDisplayed()));



    }
    @Test
    public void reset()
    {

        onView(withId(R.id.pin_entry_border)).perform((ViewAction) withText(7378));



        onView(withId(R.id.id_resend_code)).perform(click());


    }




}