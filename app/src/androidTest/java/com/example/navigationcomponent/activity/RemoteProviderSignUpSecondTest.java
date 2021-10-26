package com.example.navigationcomponent.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.widget.Button;
import android.widget.CheckBox;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mvp.omnicure.R;
import com.mvp.omnicure.utils.CustomErrorEditText;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class RemoteProviderSignUpSecondTest {
    @Rule
    public ActivityScenarioRule<RemoteProviderSignUpSecond> activityRule
            = new ActivityScenarioRule<>(RemoteProviderSignUpSecond.class);
    public RemoteProviderSignUpSecond remoteProviderSignUpSecond;


    @Test
    public void checkDisplayed() {
        onView(withId(R.id.id_create_profile_txt)).check(matches(isDisplayed()));
        onView(withId(R.id.id_remote_provider_spinner)).check(matches(isDisplayed()));
        onView(withId(R.id.id_npi_number)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_terms_and_condition)).check(matches(isDisplayed()));
        onView(withId(R.id.btnNext)).check(matches(isDisplayed()));
        onView(withId(R.id.checkbox)).check(matches(isDisplayed()));

    }
    @Test
    public void signUpRemote()
    {

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
            CustomErrorEditText npiNumber = remoteProviderSignUpSecond.findViewById(R.id.id_npi_number);
               npiNumber.setText("1234567890");
                CheckBox checkBox=remoteProviderSignUpSecond.findViewById(R.id.checkbox);
                checkBox.isChecked();

                Button btnNext = remoteProviderSignUpSecond.findViewById(R.id.btnNext);
                btnNext.performClick();
                //assertTrue(loginActivity.loginapi());
            }
        });

    }


    @After
    public void tearDown() throws Exception {
     remoteProviderSignUpSecond=null;
    }



}