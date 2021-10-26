package com.example.navigationcomponent.activity;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.mvp.omnicure.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    public LoginActivity loginActivity;

    @Before
    public void setUp() throws Exception {
        loginActivity = mActivityTestRule.getActivity();
    }
    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule
            = new ActivityScenarioRule<>(LoginActivity.class);

    Instrumentation.ActivityMonitor monitor=getInstrumentation().
            addMonitor(ActivityLocalCareProviderSignUpFirst.class.getName(),null,false);

    @Before
    public void grantPermissionCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation().executeShellCommand(
                    "pm grant " + getTargetContext().getPackageName()
                            + " android.permission.RECORDVIDEO");
        }
    }
    @Before
    public void grantPermissionAudio() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation().executeShellCommand(
                    "pm grant " + getTargetContext().getPackageName()
                            + " android.permission.AUDIO");
        }
    }
    @Before
    public void grantPermissionExternalStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation().executeShellCommand(
                    "pm grant " + getTargetContext().getPackageName()
                            + " android.permission.EXTERNALSTORAGE");
        }
    }


    @Test
    public void checkDisplayed() {
        onView(withId(R.id.id_welcome_text)).check((ViewAssertion) isDisplayed());

        onView(withId(R.id.text_body)).check((ViewAssertion) isDisplayed());
        onView(withId(R.id.id_logo_img)).check((ViewAssertion) isDisplayed());

        onView(withId(R.id.editTextUserId)).check((ViewAssertion) isDisplayed());

        onView(withId(R.id.editTextPassword)).check((ViewAssertion) isDisplayed());
        onView(withId(R.id.loginBtns)).check((ViewAssertion) isDisplayed());

        onView(withId(R.id.editTextPassword)).check((ViewAssertion) isDisplayed());
        onView(withId(R.id.password_visibility)).check((ViewAssertion) isDisplayed());

        onView(withId(R.id.id_forgot_password)).check((ViewAssertion) isDisplayed());
        onView(withId(R.id.signin_auth)).check((ViewAssertion) isDisplayed());
        onView(withId(R.id.id_signup_text)).check((ViewAssertion) isDisplayed());


    }
    @Test
     public void login()
     {

         onView(withId(R.id.editTextUserId))
                 .perform(typeText("remote@gmail.com"), closeSoftKeyboard());
         onView(withId(R.id.editTextPassword)).perform(typeText("Omni@123"),closeSoftKeyboard());

         onView(withId(R.id.loginBtns)).perform(click());
         onView(withId(R.id.id_signup_text)).perform(click());

     }

    @Test
    public void buttonShouldUpdateText(){
        onView(withId(R.id.loginBtns)).perform(click());

    }


    public void intent()
    {
        assertNotNull(loginActivity.findViewById(R.id.loginBtns));
        onView(withId(R.id.loginBtns)).perform(click());
        Activity lcp=getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(lcp);
        lcp.finish();
    }

    @Test
    public void testLogin(){
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                EditText email = loginActivity.findViewById(R.id.editTextUserId);
                EditText pass = loginActivity.findViewById(R.id.editTextPassword);
                email.setText("email");
                pass.setText("pass");
                Button loginBtn = loginActivity.findViewById(R.id.signin_btn);
                loginBtn.performClick();
                //assertTrue(loginActivity.loginapi());
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }


/*
    @Test
    public void testPressBackButton() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack();
    }

    @Test
    public void testUiDevice() throws RemoteException {
        UiDevice device = UiDevice.getInstance(
                InstrumentationRegistry.getInstrumentation());
        if (device.isScreenOn()) {
            device.setOrientationLeft();
            device.openNotification();
        }
    }

    @Ignore
    @Test
    public void testUiAutomatorAPI() throws UiObjectNotFoundException, InterruptedException {
        activityRule.getScenario().moveToState(Lifecycle.State.STARTED);
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiSelector editTextSelectorEmail = new UiSelector().className("androidx.widget.EditText").text("abc@gmail.com").focusable(true);
        UiObject editTextWidgetEmail = device.findObject(editTextSelectorEmail);
        editTextWidgetEmail.setText("abc@gmail.com");

        Thread.sleep(2000);
        UiSelector editTextSelectorPassword = new UiSelector().className("androidx.widget.EditText").text("Omni@123").focusable(true);
        UiObject editTextWidgetPassword = device.findObject(editTextSelectorPassword);
        editTextWidgetPassword.setText("Omni@123");

        UiSelector buttonSelector = new UiSelector().className("androidx.widget.Button").text("Login").clickable(true);
        UiObject buttonWidget = device.findObject(buttonSelector);
        buttonWidget.click();

        Thread.sleep(2000);
    }*/
}