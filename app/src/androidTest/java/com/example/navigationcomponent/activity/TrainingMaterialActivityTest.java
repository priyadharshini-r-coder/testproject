package com.example.navigationcomponent.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Lifecycle;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mvp.omnicure.R;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class TrainingMaterialActivityTest {
    @Rule
    public ActivityScenarioRule<TrainingMaterialActivity> scenarioRule
            = new ActivityScenarioRule<>(TrainingMaterialActivity.class);



    @Test
    public void setUp() throws Exception {
        scenarioRule.getScenario().moveToState(Lifecycle.State.STARTED);
        onView(withId(R.id.webView)).check(matches(isDisplayed()));
        matchToolbarTitle("Urgent");
    }

    @NonNull
    @Test
    private static ViewInteraction matchToolbarTitle(
            CharSequence title) {
        return onView(isAssignableFrom(Toolbar.class))
                .check(matches(isDisplayed()));
    }
    @After
    public void tearDown() throws Exception {
    }

}