package com.example.navigationcomponent.activity;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Activity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.test.rule.ActivityTestRule;

import com.mvp.omnicure.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class FilterActivityTest {


    private Activity mActivity;
    private RadioGroup mRadioGroup;

    @Rule
    public ActivityTestRule<FilterActivity> mActivityRule =
            new ActivityTestRule<>(FilterActivity.class);
    public FilterActivity filterActivity;

    @Before
    public void setUp() throws Exception {
       filterActivity = mActivityRule.getActivity();
    }
    @Before
    public void setup() {
        mActivity = mActivityRule.getActivity();
        mRadioGroup = (RadioGroup) mActivity.findViewById(R.id.radioGroup);
    }
    @Test
    public void testConstructors() {
        new RadioGroup(mActivity);
      //  AttributeSet attrs = getAttributeSet(R.layout.activity_filter);
        //new RadioGroup(mActivity, attrs);
        new RadioGroup(mActivity, null);
    }




    @Test
    public void testFilter(){
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
             RadioButton radioAll = filterActivity.findViewById(R.id.radioAll);
             radioAll.performClick();
             radioAll.isChecked();
             RadioButton radioActive = filterActivity.findViewById(R.id.radioActive);
             radioActive.performClick();
             radioActive.isChecked();
             RadioButton radioPending = filterActivity.findViewById(R.id.radioPending);
             radioPending.performClick();
             radioPending.isChecked();
             RadioButton radioAssigned = filterActivity.findViewById(R.id.radioAssigned);
             radioAssigned.isChecked();
             radioAssigned.performClick();

                Button saveBtn = filterActivity.findViewById(R.id.saveBtn);
                saveBtn.performClick();
                //assertTrue(loginActivity.loginapi());
            }
        });
    }

    @After
    public void tearDown() throws Exception {
       filterActivity = null;
    }
}