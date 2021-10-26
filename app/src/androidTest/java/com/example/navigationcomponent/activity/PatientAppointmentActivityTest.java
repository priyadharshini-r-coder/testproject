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

public class PatientAppointmentActivityTest {

    @Rule
    public ActivityTestRule<PatientAppointmentActivity> mActivityTestRule = new ActivityTestRule<PatientAppointmentActivity>(PatientAppointmentActivity.class);

    public PatientAppointmentActivity patientAppointmentActivity;

    @Before
    public void setUp() throws Exception {
      patientAppointmentActivity= mActivityTestRule.getActivity();
    }

    @Test
    public void setPatientAppointmentActivity(){
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                EditText firstName = patientAppointmentActivity.findViewById(R.id.id_first_name);
                EditText lastName = patientAppointmentActivity.findViewById(R.id.id_last_name);
                EditText dob = patientAppointmentActivity.findViewById(R.id.id_dob);
               // EditText gender = patientAppointmentActivity.findViewById(R.id.id_last_name);
                EditText phoneNumber= patientAppointmentActivity.findViewById(R.id.id_phone_number);
                EditText email = patientAppointmentActivity.findViewById(R.id.id_email_id);
                EditText pass = patientAppointmentActivity.findViewById(R.id.editTextPassword);
                CheckBox checkBox=patientAppointmentActivity.findViewById(R.id.addRelativeCheckBox);
                checkBox.performClick();

                firstName.setText("firstname");
                lastName.setText("lastname");
                dob.setText("02-04-1997");
                phoneNumber.setText("8765677899");
                email.setText("testrp@gmail.com");
                pass.setText("Omni@123");
                Button submitBTn=patientAppointmentActivity.findViewById(R.id.submitBtn);
                submitBTn.performClick();
                //assertTrue(loginActivity.loginapi());
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        patientAppointmentActivity = null;
    }
}