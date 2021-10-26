package com.example.navigationcomponent.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.widget.Button;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import com.mvp.omnicure.R;
import com.mvp.omnicure.viewmodel.TermsAndConditionsViewModel;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

public class TermsAndConditionsActivityTest {
    private TermsAndConditionsViewModel viewModel;
    @Rule
    public ActivityTestRule<TermsAndConditionsActivity> mActivityTestRule = new ActivityTestRule<TermsAndConditionsActivity>(TermsAndConditionsActivity.class);

    public TermsAndConditionsActivity termsAndConditionsActivity;
    @Test
    public void setUp() throws Exception {

        viewModel=new TermsAndConditionsViewModel();

    }

    @Test
    public void checkDisplayed(){
        onView(withId(R.id.terms_txt)).check(matches(isDisplayed()));

        onView(withId(R.id.end_user)).check(matches(isDisplayed()));
        onView(withId(R.id.agreement_text)).check(matches(isDisplayed()));


        onView(withId(R.id.agreeBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void testLogin(){
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
               TextView endUser = termsAndConditionsActivity.findViewById(R.id.end_user);
               TextView agreement = termsAndConditionsActivity.findViewById(R.id.agreement_text);

                Button agreeBtn = termsAndConditionsActivity.findViewById(R.id.agreeBtn);
                agreeBtn.performClick();
                //assertTrue(loginActivity.loginapi());
            }
        });
    }





    @Test
    public void getTerms()
    {

        viewModel.getTerms();
    }
    @After
    public void tearDown() throws Exception {
        termsAndConditionsActivity= null;
    }

}