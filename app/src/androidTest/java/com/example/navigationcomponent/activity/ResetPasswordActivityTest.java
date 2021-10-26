package com.example.navigationcomponent.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.mvp.omnicure.R;
import com.mvp.omnicure.viewmodel.ResetPasswordViewModel;

import org.junit.Rule;
import org.junit.Test;

public class ResetPasswordActivityTest {
    @Rule
    public ActivityScenarioRule<ResetPasswordActivity> activityRule
            = new ActivityScenarioRule<>(ResetPasswordActivity.class);
    public ResetPasswordViewModel viewModel=new ResetPasswordViewModel();

    @Test
    public void checkDisplayed() {
        onView(withId(R.id.edtOldPassword)).check(matches(isDisplayed()));

        onView(withId(R.id.edtNewPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.edtConfirmPassword)).check(matches(isDisplayed()));
    }







}