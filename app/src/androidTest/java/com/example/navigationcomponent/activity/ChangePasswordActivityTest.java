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

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.mvp.omnicure.R;

import org.junit.Rule;
import org.junit.Test;

public class ChangePasswordActivityTest {

    @Rule
    public ActivityScenarioRule<ChangePasswordActivity> activityRule
            = new ActivityScenarioRule<>(ChangePasswordActivity.class);

    @Test
    public void checkDisplayed() {
        onView(withId(R.id.id_logo_img)).check(matches(isDisplayed()));

        onView(withId(R.id.resetPasswordTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.resetPasswordDesc)).check(matches(isDisplayed()));

        onView(withId(R.id.edtNewPassword)).check(matches(isDisplayed()));

        onView(withId(R.id.edtConfirmPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.txtSavePassword)).check(matches(isDisplayed()));

        onView(withId(R.id.id_back_button)).check(matches(isDisplayed()));



    }
    @Test
    public void reset()
    {

        onView(withId(R.id.edtNewPassword))
                .perform(typeText("Omni@123"), closeSoftKeyboard());
        onView(withId(R.id.edtConfirmPassword)).perform(typeText("Omni@123"),closeSoftKeyboard());

        onView(withId(R.id.txtSavePassword)).perform(click());
        onView(withId(R.id.id_back_button)).perform(click());

    }

    @Test
    public void buttonShouldUpdateText(){
        onView(withId(R.id.txtSavePassword)).perform(click());
        onView(withId(getResourceId("Save new password"))).check(matches(withText("Save new password")));
    }
    private static int getResourceId(String s) {
        Context targetContext = getTargetContext();
        String packageName = targetContext.getPackageName();
        return targetContext.getResources().getIdentifier(s, "id", packageName);
    }

}