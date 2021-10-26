package com.example.navigationcomponent.activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.widget.Button;
import android.widget.EditText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.mvp.omnicure.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class ContactAdminActivityTest {

    @Rule
    public ActivityTestRule<ContactAdminActivity> activityTestRule = new ActivityTestRule<>(ContactAdminActivity.class);
    ContactAdminActivity adminActivity;

    @Test
    public void checkDisplayed() {
        onView(withId(R.id.textAdmin)).check(matches(isDisplayed()));
        onView(withId(R.id.btnSubmit)).check(matches(isDisplayed()));


    }
    @Test
    public void contactAdmin()
    {

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                EditText textAdmin = adminActivity.findViewById(R.id.textAdmin);

                textAdmin.setText("Contact admin");

                Button loginBtn = adminActivity.findViewById(R.id.btnSubmit);
                loginBtn.performClick();
                //assertTrue(loginActivity.loginapi());
            }
        });
    }

 /*   @Test
    public void testUI() {
        Activity activity = activityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.textAdmin));
        EditText helloView = activity.findViewById(R.id.textAdmin);
        assertTrue(helloView.isShown());
        assertEquals("Hello World!", helloView.getText());
        assertEquals(InstrumentationRegistry.getTargetContext().getString(R.string.email_id), helloView.getText());
        assertNull(activity.findViewById(android.R.id.button1));
    }

    @Test
    @RequiresDevice
    public void testRequiresDevice() {
        Log.d("Test Filters", "This test requires a device");
        Activity activity = activityTestRule.getActivity();
        assertNotNull("MainActivity is not available", activity);
    }

    @Test
    @SdkSuppress(minSdkVersion = 15)
    public void testMinSdkVersion() {
        Log.d("Test Filters", "Checking for min sdk >= 15");
        Activity activity = activityTestRule.getActivity();
        assertNotNull("MainActivity is not available", activity);
    }

    @Test
    @SdkSuppress(minSdkVersion = Build.VERSION_CODES.LOLLIPOP)
    public void testMinBuild() {
        Log.d("Test Filters", "Checking for min build > Lollipop");
        Activity activity = activityTestRule.getActivity();
        assertNotNull("MainActivity is not available", activity);
    }

    @Test
    @SmallTest
    public void testSmallTest() {
        Log.d("Test Filters", "this is a small test");
        Activity activity = activityTestRule.getActivity();
        assertNotNull("MainActivity is not available", activity);
    }

    @Test
    @LargeTest
    public void testLargeTest() {
        Log.d("Test Filters", "This is a large test");
        Activity activity = activityTestRule.getActivity();
        assertNotNull("MainActivity is not available", activity);
    }
*/
 /*   @Test
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
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiSelector editTextSelector = new UiSelector().className("android.widget.EditText").text("this is a test").focusable(true);
        UiObject editTextWidget = device.findObject(editTextSelector);
        editTextWidget.setText("this is new text");

        Thread.sleep(2000);

        UiSelector buttonSelector = new UiSelector().className("android.widget.Button").text("CLICK ME").clickable(true);
        UiObject buttonWidget = device.findObject(buttonSelector);
        buttonWidget.click();

        Thread.sleep(2000);
    }*/

   /* @Test
    public void testEspresso() {
        ViewInteraction interaction =
                onView(allOf(withId(R.id.textAdmin),
                        withText("this is a test"),
                        hasFocus()));
        interaction.perform(replaceText("how about some new text"));
        ViewInteraction interaction2 =
                onView(allOf(withId(R.id.textAdmin),
                        withText("how about some new text")));
        interaction2.check(matches(hasFocus()));
    }

    @Test
    public void testEspressoSimplified() {
        onView(allOf(withId(R.id.textAdmin),
                withText("this is a test"),
                hasFocus())).perform(replaceText("how about some new text"));
        onView(allOf(withId(R.id.textAdmin),
                withText("how about some new text"))).check(matches(hasFocus()));
    }*/
/*

    @Test
    public void testAdd() {
        Button addButton = mock(Button.class);
        when(addButton.getId()).thenReturn(R.id.submitBtn);

     ContactAdminActivity activity = activityTestRule.getActivity();
        activity.onSuccess();

    }
*/

   /* @Test
    public void testRemove() {
        Button removeButton = mock(Button.class);
        when(removeButton.getId()).thenReturn(R.id.buttonRemove);

        MainActivity activity = activityTestRule.getActivity();
        activity.buttonClicked(removeButton);
    }*/
}