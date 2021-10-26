package com.example.navigationcomponent.activity;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mvp.omnicure.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ForgotPasswordActivityTest {
    @Rule
    public ActivityScenarioRule<ForgotPasswordActivity> activityRule
            = new ActivityScenarioRule<>(ForgotPasswordActivity.class);

    @Test
    public void checkDisplayed() {
        onView(withId(R.id.id_logo_img)).check(matches(isDisplayed()));

        onView(withId(R.id.resetPasswordTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.resetPasswordDesc)).check(matches(isDisplayed()));

        onView(withId(R.id.rbEmailAddress)).check(matches(isDisplayed()));

        onView(withId(R.id.rbPhoneNumber)).check(matches(isDisplayed()));
        onView(withId(R.id.edtEmailAddress)).check(matches(isDisplayed()));

        onView(withId(R.id.txtBackToSignIn)).check(matches(isDisplayed()));



    }
    @Test
    public void reset()
    {

        onView(withId(R.id.edtEmailAddress)).perform(typeText("remote@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.editTextPassword)).perform(typeText("Omni@123"),closeSoftKeyboard());

        onView(withId(R.id.txtResetPassword)).perform(click());
        onView(withId(R.id.txtBackToSignIn)).perform(click());

    }

    @Test
    public void buttonShouldUpdateText(){
        onView(withId(R.id.loginBtns)).perform(click());
        onView(withId(getResourceId("Reset Password"))).check(matches(withText("Reset Password")));
    }
    private static int getResourceId(String s) {
        Context targetContext = getTargetContext();
        String packageName = targetContext.getPackageName();
        return targetContext.getResources().getIdentifier(s, "id", packageName);
    }


    @Test
    public void intentPrep() {
        Intent intent = new Intent();
        intent.putExtra("EXTRA", "Reset Password");
        activityRule.getScenario().moveToState(Lifecycle.State.STARTED);
         onView(withId(R.id.txtResetPassword)).check(matches(withText("Reset Password")));
    }




}