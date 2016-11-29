package com.example.sampleandroid.test;

import com.example.sampleandroid.MyService;

import android.content.Intent;
import android.test.ServiceTestCase;
import android.util.Log;



/**
 * Test class for the Alarm sample test package. This test class tests the AlarmService
 * service component.
 */
public class MainServiceTest extends ServiceTestCase<MyService> {
    // Contains an Intent used to start the service
    Intent mStartServiceIntent;

    // Contains a handle to the system alarm service
    MyService mService;

    /**
     * Constructor for the test class. Test classes that are run by InstrumentationTestRunner
     * must provide a constructor with no arguments that calls the base class constructor as its
     * first statement.
     */
    public MainServiceTest() {
        super(MyService.class);
    }

    /*
     * Sets up the test fixture. This method is called before each test
     */
    @Override
    protected void setUp() throws Exception {

        super.setUp();

        // Sets up an intent to start the service under test
        mStartServiceIntent = new Intent(this.getSystemContext(),MainServiceTest.class);
    }

    /**
     * Cleans up the test fixture
     * Called after each test method. If you override the method, call super.tearDown() as the
     * last statement in your override.
     */
    @Override
    protected void tearDown() throws Exception {
        // Always call the super constructor when overriding tearDown()
        super.tearDown();
    }

    /**
     * Tests the service's onCreate() method. Starts the service using startService(Intent)
     */
    public void testServiceCreate() {
        // Starts the service under test
    	 Log.i("MainServicetest", "~~~~111mService :"+mService);
        this.startService(mStartServiceIntent);

        // Gets a handle to the service under test.
        mService = this.getService();
        Log.i("MainServicetest", "~~~~222mService :"+mService);
        // Asserts that the Notification Manager was created in the service under test.
        assertNotNull(mService);
        Log.i("MainServicetest", "~~~~Is Running:"+mService.isRunning());
        mService.stopService(mStartServiceIntent);

    }
    

}
