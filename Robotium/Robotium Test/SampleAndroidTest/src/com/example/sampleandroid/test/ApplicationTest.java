package com.example.sampleandroid.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.InstrumentationTestCase;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.example.sampleandroid.MainActivity;
import com.example.sampleandroid.MainCalculatorActivity;

public class ApplicationTest extends InstrumentationTestCase {

	@MediumTest
	public void testAValidUserCanLogIn() {

		Instrumentation instrumentation = getInstrumentation();
		// Register we are interested in the authentication activiry...
		Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(
				MainActivity.class.getName(), null, false);

		// Start the MainActivity activity as the first activity...
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClassName(instrumentation.getTargetContext(),
				MainActivity.class.getName());
		instrumentation.startActivitySync(intent);

		// Wait for it to start...
		Activity currentActivity = getInstrumentation()
				.waitForMonitorWithTimeout(monitor, 5);
		// assertThat(currentActivity, is(notNullValue()));
		monitor = instrumentation.addMonitor(
				MainCalculatorActivity.class.getName(), null, false);
		View currentView = currentActivity
				.findViewById(com.example.sampleandroid.R.id.btnGotoCalculator);
		TouchUtils.clickView(this, currentView);

		// Wait for the calculator page to start...
		Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(
				monitor, 1000);
		Log.i("TAG", "Current Activity :" + currentActivity + " : "
				+ nextActivity);

		// Get First Edit text to enter data
		currentView = nextActivity
				.findViewById(com.example.sampleandroid.R.id.EditText01);
		assertTrue(currentView instanceof EditText);
		TouchUtils.clickView(this, currentView);
		instrumentation.sendStringSync("10");
		// Get Second Edit text to enter data
		currentView = nextActivity
				.findViewById(com.example.sampleandroid.R.id.EditText02);
		assertTrue(currentView instanceof EditText);
		TouchUtils.clickView(this, currentView);
		instrumentation.sendStringSync("20");

		// Get current view ie Button
		currentView = nextActivity
				.findViewById(com.example.sampleandroid.R.id.Button01);
		TouchUtils.clickView(this, currentView);
		// nextActivity.finish();
		// Press back button to finish the activity
		instrumentation.sendCharacterSync(KeyEvent.KEYCODE_BACK);// nextActivity.finish();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//finish the app
		instrumentation.sendCharacterSync(KeyEvent.KEYCODE_BACK);

	}
}
