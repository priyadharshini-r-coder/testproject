package com.example.navigationcomponent.activity;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.test.rule.ActivityTestRule;

import com.mvp.omnicure.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PatientAppointmentActivityInfoTest {

    @Rule
    public ActivityTestRule<PatientAppointmentActivityInfo> mActivityTestRule = new ActivityTestRule<PatientAppointmentActivityInfo>(PatientAppointmentActivityInfo.class);

    public PatientAppointmentActivityInfo patientAppointmentActivityInfo;

    @Before
    public void setUp() throws Exception {
        patientAppointmentActivityInfo= mActivityTestRule.getActivity();
    }

    @Test
    public void setPatientAppointmentActivity(){
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                EditText message= patientAppointmentActivityInfo.findViewById(R.id.messageTxt);


                CheckBox checkBox=patientAppointmentActivityInfo.findViewById(R.id.agreementCheckBox);
                checkBox.performClick();
                Button submitBTn=patientAppointmentActivityInfo.findViewById(R.id.submitBtn);
                submitBTn.performClick();

                message.setText("High Fever cold and cough");


                //assertTrue(loginActivity.loginapi());
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        patientAppointmentActivityInfo = null;
    }

}