package com.example.navigationcomponent.activity;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.test.rule.ActivityTestRule;

import com.mvp.omnicure.R;
import com.mvp.omnicure.viewmodel.TransferPatientViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import omnicure.mvp.com.patientEndpoints.model.PatientTransferRequest;

public class TransferPatientActivityTest {
    @Rule
    public ActivityTestRule<TransferPatientActivity> mActivityTestRule = new ActivityTestRule<TransferPatientActivity>(TransferPatientActivity.class);

    public TransferPatientActivity transferPatientActivity;
    private TransferPatientViewModel viewModel;
    PatientTransferRequest request;

    @Before
    public void setUp() throws Exception {
        transferPatientActivity = mActivityTestRule.getActivity();
        viewModel=new TransferPatientViewModel();
        request=new PatientTransferRequest();
    }

    @Test
    public void testTransferPatient(){
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                RadioButton transferWithinHospital = transferPatientActivity.findViewById(R.id.rbTransferWithInHospital);
                RadioButton transferAnotherHospital = transferPatientActivity.findViewById(R.id.rbTransferAnotherHospital);
                //EditText email = transferPatientActivity.findViewById(R.id.);
               EditText summaryNote= transferPatientActivity.findViewById(R.id.edtSummaryNote);
               // EditText phonenumber = .findViewById(R.id.id_phone_number);
                Spinner spinner=transferPatientActivity.findViewById(R.id.spinnerWithInWard);
                Spinner spinnerHospital=transferPatientActivity.findViewById(R.id.spinnerWithinProvider);
               // CheckBox checkBox=transferPatientActivity.findViewById(R.id.agreementCheckBox);
                transferAnotherHospital.performClick();
                transferWithinHospital.performClick();
                spinner.performClick();
                spinnerHospital.performClick();
                summaryNote.setText("discharge patient");
                //checkBox.isChecked();


                //Button loginBtn = registrationActivity.findViewById(R.id.signin_btn);
                //loginBtn.performClick();
                //assertTrue(loginActivity.loginapi());
            }
        });
    }
    @Test
    public void transferPatientToAnotherHospital() {

        viewModel.transferPatientToAnotherHospital("",request);
    }
    @Test
    public void transferPatientWithInHospital() {

        viewModel.transferPatientWithInHospital("",request);
    }

    @Test
    public void getWardsList() {

        viewModel.getWardsList(9087666L);
    }

    @Test
    public void getHospitallist() {

        viewModel.getHospitalList("","5678899");
    }
    @Test
    public void getProviderListResponse() {

        viewModel.getProviderListResponse(78655L,987765L);
    }

    @After
    public void tearDown() throws Exception {
        transferPatientActivity= null;
    }

}