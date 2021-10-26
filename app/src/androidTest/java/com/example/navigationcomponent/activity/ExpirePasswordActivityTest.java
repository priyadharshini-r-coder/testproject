package com.example.navigationcomponent.activity;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.rule.ActivityTestRule;

import com.mvp.omnicure.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ExpirePasswordActivityTest {

    @Rule
    public ActivityTestRule<ExpirePasswordActivity> activityRule
            = new ActivityTestRule<>(ExpirePasswordActivity.class);

   ExpirePasswordActivity expirePasswordActivity;
    @Before
    public void setUp() throws Exception
    {
    expirePasswordActivity = activityRule.getActivity();
    }

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
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                EditText newPassword =expirePasswordActivity .findViewById(R.id.edtNewPassword);

                EditText cnfmPassword = expirePasswordActivity.findViewById(R.id.edtConfirmPassword);
                newPassword.setText("Omni@123");

                 cnfmPassword.setText("Omni@123");
                Button saveBtn = expirePasswordActivity.findViewById(R.id.txtSavePassword);
                saveBtn.performClick();
                //assertTrue(loginActivity.loginapi());
            }
        });

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