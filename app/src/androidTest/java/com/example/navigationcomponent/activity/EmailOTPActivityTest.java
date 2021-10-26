package com.example.navigationcomponent.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import androidx.test.rule.ActivityTestRule;

import com.mvp.omnicure.R;
import com.mvp.omnicure.customview.PinEntryView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class EmailOTPActivityTest {
    @Rule
    public ActivityTestRule<EmailOTPActivity> mActivityTestRule = new ActivityTestRule<EmailOTPActivity>(EmailOTPActivity.class);

    public EmailOTPActivity emailOTPActivity;


    @Test
    public void checkDisplayed() {
        onView(withId(R.id.otpMsg))
                .check(matches((isDisplayed())));

        onView(withId(R.id.pin_entry_border)).check(matches(isDisplayed()));



    }

    @Before
    public void setUp() throws Exception {
      emailOTPActivity= mActivityTestRule.getActivity();
    }

    @Test
    public void setEmailOTPActivity(){
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                PinEntryView view = emailOTPActivity.findViewById(R.id.pin_entry_border);
                view.setText("7278");
                //assertTrue(view.isLongClickable());


                //assertTrue(loginActivity.loginapi());
            }
        });
    }

    @After
    public void tearDown() throws Exception {
      emailOTPActivity= null;
    }

}