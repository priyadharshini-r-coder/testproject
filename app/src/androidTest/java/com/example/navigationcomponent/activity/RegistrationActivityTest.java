package com.example.navigationcomponent.activity;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.test.rule.ActivityTestRule;

import com.mvp.omnicure.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class RegistrationActivityTest {

    @Rule
    public ActivityTestRule<RegistrationActivity> mActivityTestRule = new ActivityTestRule<RegistrationActivity>(RegistrationActivity.class);

    public RegistrationActivity registrationActivity;

    @Before
    public void setUp() throws Exception {
        registrationActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testRegister(){
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                EditText firstName = registrationActivity.findViewById(R.id.id_first_name);
                EditText lastName = registrationActivity.findViewById(R.id.id_last_name);
                EditText email = registrationActivity.findViewById(R.id.id_email_id);
                EditText pass = registrationActivity.findViewById(R.id.id_password);
                EditText phonenumber = registrationActivity.findViewById(R.id.id_phone_number);
                Spinner spinner=registrationActivity.findViewById(R.id.id_lcp_provider_type_spinner);
                Spinner spinnerHospital=registrationActivity.findViewById(R.id.id_spinner_hospital);
                CheckBox checkBox=registrationActivity.findViewById(R.id.agreementCheckBox);
                firstName.setText("Priya");
                lastName.setText("dharshini");
                email.setText("testrp@gmail.com");
                pass.setText("Omni@123");
                phonenumber.setText("987677656");
                spinner.performClick();
                spinnerHospital.performClick();
                checkBox.isChecked();


                //Button loginBtn = registrationActivity.findViewById(R.id.signin_btn);
               //loginBtn.performClick();
                //assertTrue(loginActivity.loginapi());
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        registrationActivity= null;
    }
}