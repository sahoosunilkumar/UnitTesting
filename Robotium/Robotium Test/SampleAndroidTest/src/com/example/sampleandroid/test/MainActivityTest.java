package com.example.sampleandroid.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.example.sampleandroid.MainActivity;
import com.example.sampleandroid.MainCalculatorActivity;
import com.jayway.android.robotium.solo.Solo;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{
	private Solo solo;
	public MainActivityTest() {
		super(MainActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	/**
	 * Test whether calculator button is clicked or not
	 */
	public void testButtonClick() {
		//Retreive the button depending on the id of button
		View calculatorButton =  solo.getView(com.example.sampleandroid.R.id.btnGotoCalculator);
		 solo.clickOnView(calculatorButton);
		 assertTrue(solo.waitForActivity(MainCalculatorActivity.class.getSimpleName()));
		}
	
	@Override
	protected void tearDown() throws Exception{
			solo.finishOpenedActivities();
	}

}
