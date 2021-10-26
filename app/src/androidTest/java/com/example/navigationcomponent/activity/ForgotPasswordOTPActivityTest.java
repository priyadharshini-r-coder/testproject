package com.example.navigationcomponent.activity;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.content.Context;
import android.widget.Button;

import androidx.test.rule.ActivityTestRule;

import com.mvp.omnicure.R;
import com.mvp.omnicure.customview.PinEntryView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ForgotPasswordOTPActivityTest {
    @Rule
    public ActivityTestRule<ForgotPasswordOTPActivity> activityRule
            = new ActivityTestRule<>(ForgotPasswordOTPActivity.class);
    ForgotPasswordOTPActivity otpActivity;
    @Before
    public void setUp() throws Exception {
     otpActivity= activityRule.getActivity();
    }

    @Test
    public void checkDisplayed() {
        onView(withId(R.id.id_logo_img)).check(matches(isDisplayed()));

        onView(withId(R.id.resetPasswordTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.resetPasswordDesc)).check(matches(isDisplayed()));

        onView(withId(R.id.pin_entry_border)).check(matches(isDisplayed()));

        onView(withId(R.id.txtResetPassword)).check(matches(isDisplayed()));


        onView(withId(R.id.id_back_button)).check(matches(isDisplayed()));



    }
    @Test
    public void forgotOTp()
    {

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                PinEntryView pinEntryView =otpActivity.findViewById(R.id.pin_entry_border);
                pinEntryView.setText("7378");
                Button resetBtn = otpActivity.findViewById(R.id.txtResetPassword);
                resetBtn.performClick();
                otpActivity=new ForgotPasswordOTPActivity();
                otpActivity.doForgotPasswordOTP("7378");
                //assertTrue(loginActivity.loginapi());
            }
        });


    }

    @Test
    public void buttonShouldUpdateText(){
        //onView(withId(R.id.txtResetPassword).matches("Reset Password"));
        onView(withId(getResourceId("Reset Password"))).check(matches(withText("Reset Password")));
    }
    private static int getResourceId(String s) {
        Context targetContext = getTargetContext();
        String packageName = targetContext.getPackageName();
        return targetContext.getResources().getIdentifier(s, "id", packageName);
    }



}