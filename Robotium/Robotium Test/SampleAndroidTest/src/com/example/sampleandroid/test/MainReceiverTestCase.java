package com.example.sampleandroid.test;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.example.sampleandroid.OutgoingCallReceiver;

public class MainReceiverTestCase extends AndroidTestCase{

	private OutgoingCallReceiver mReceiver;
    private TestContext mContext;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mReceiver = new OutgoingCallReceiver();
        mContext = new TestContext();
    }
    /**
     * Test the out going call functionality
     * Make a call. and test whether the call is made to correct number or not.
     */
    public void testSuccessTestCase()
    {
    	long phoneNumberToBeDialled = getPhoneNumber();
    	Intent intent = new Intent(Intent.ACTION_NEW_OUTGOING_CALL);
        intent.putExtra(Intent.EXTRA_PHONE_NUMBER, ""+phoneNumberToBeDialled);

        mReceiver.onReceive(mContext, intent);        
        assertEquals(1, mContext.getReceivedIntents().size());
        assertNull(mReceiver.getResultData());

        Intent receivedIntent = mContext.getReceivedIntents().get(0);
        assertNull(receivedIntent.getAction());
        //test whether phonenumber to be dialled is same as that of dialled one
        assertEquals(""+phoneNumberToBeDialled, receivedIntent.getStringExtra("phoneNum"));
        assertTrue((receivedIntent.getFlags() & Intent.FLAG_ACTIVITY_NEW_TASK) != 0);
   
    }
    /**
     * Test the out going call functionality
     * Make a call. and test whether the call is made to correct number or not. 
     * This is a failure test case
     */
    public void testFailureTestCase()
    {
    	long phoneNumberToBeDialled = getPhoneNumber();
    	Intent intent = new Intent(Intent.ACTION_NEW_OUTGOING_CALL);
        intent.putExtra(Intent.EXTRA_PHONE_NUMBER, ""+phoneNumberToBeDialled);

        mReceiver.onReceive(mContext, intent);        
        assertEquals(1, mContext.getReceivedIntents().size());
        assertNull(mReceiver.getResultData());

        Intent receivedIntent = mContext.getReceivedIntents().get(0);
        assertNull(receivedIntent.getAction());
        //test whether phonenumber to be dialled is same as that of dialled one
        assertEquals("1"+phoneNumberToBeDialled, receivedIntent.getStringExtra("phoneNum"));
        assertTrue((receivedIntent.getFlags() & Intent.FLAG_ACTIVITY_NEW_TASK) != 0);
   
    }
    
    private long getPhoneNumber(){
    	return (long) (Math.random()*10000000000L);
    }
}
