package com.example.sampleandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnSendSMS;
	Button btnGotoService;
	Button btnGotoCalculator;
	Button btnGotoProvider;
	EditText txtPhoneNo;
	EditText txtMessage;
	SMSReceiver smsReceiver = null;
	OutgoingCallReceiver outgoingCallReceiver = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
		btnGotoService = (Button) findViewById(R.id.btnGotoService);
		btnGotoCalculator = (Button) findViewById(R.id.btnGotoCalculator);
		btnGotoProvider = (Button) findViewById(R.id.btnGotoProvider);
		btnSendSMS.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((Button) v).getText().equals(
						getResources().getText(R.string.btn_register)
								.toString())) {
					((Button) v).setText(getResources().getText(
							R.string.btn_unregister).toString());
					registerSMSReceiver();
					registerOutgoingCallReceiver();
				} else {
					unRegisterSMSReceiver();
					unRegisterOutgoingCallReceiver();
					((Button) v).setText(getResources().getText(
							R.string.btn_register).toString());
				}

			}
		});
		btnGotoService.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent serviceIntent = new Intent(MainActivity.this,
						MainServiceActivity.class);
				startActivity(serviceIntent);

			}
		});
		btnGotoCalculator.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent serviceIntent = new Intent(MainActivity.this,
						MainCalculatorActivity.class);
				startActivity(serviceIntent);

			}
		});
		
		btnGotoProvider.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent serviceIntent = new Intent(MainActivity.this,
						TodosOverviewActivity.class);
				startActivity(serviceIntent);

			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void registerSMSReceiver() {
		if (smsReceiver == null) {
			smsReceiver = new SMSReceiver();
		}
		IntentFilter filter = new IntentFilter(
				"android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(100000);
		registerReceiver(smsReceiver, filter);
		Toast.makeText(MainActivity.this, "Registering sms receiver",
				Toast.LENGTH_LONG).show();
	}

	private void unRegisterSMSReceiver() {
		if (smsReceiver != null) {
			unregisterReceiver(smsReceiver);
		}
		Toast.makeText(MainActivity.this, "Un registering sms receiver",
				Toast.LENGTH_LONG).show();
	}
	
	
	private void registerOutgoingCallReceiver() {
		if (outgoingCallReceiver == null) {
			outgoingCallReceiver = new OutgoingCallReceiver();
		}
		IntentFilter filter = new IntentFilter(
				"android.intent.action.NEW_OUTGOING_CALL");
		filter.setPriority(100000);
		registerReceiver(outgoingCallReceiver, filter);
		
	}

	private void unRegisterOutgoingCallReceiver() {
		if (outgoingCallReceiver != null) {
			unregisterReceiver(outgoingCallReceiver);
		}
		Toast.makeText(MainActivity.this, "Un registering sms receiver",
				Toast.LENGTH_LONG).show();
	}

	/*
	 * //---sends a SMS message to another device--- private void sendSMS(String
	 * phoneNumber, String message) {
	 *//*
		 * PendingIntent pi = PendingIntent.getActivity(this, 0, new
		 * Intent(this, test.class), 0); SmsManager sms =
		 * SmsManager.getDefault(); sms.sendTextMessage(phoneNumber, null,
		 * message, pi, null);
		 *//*
			 * 
			 * String SENT = "SMS_SENT"; String DELIVERED = "SMS_DELIVERED";
			 * 
			 * PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new
			 * Intent(SENT), 0);
			 * 
			 * PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
			 * new Intent(DELIVERED), 0);
			 * 
			 * //---when the SMS has been sent--- registerReceiver(new
			 * BroadcastReceiver(){
			 * 
			 * @Override public void onReceive(Context arg0, Intent arg1) {
			 * switch (getResultCode()) { case Activity.RESULT_OK:
			 * Toast.makeText(getBaseContext(), "SMS sent",
			 * Toast.LENGTH_SHORT).show(); break; case
			 * SmsManager.RESULT_ERROR_GENERIC_FAILURE:
			 * Toast.makeText(getBaseContext(), "Generic failure",
			 * Toast.LENGTH_SHORT).show(); break; case
			 * SmsManager.RESULT_ERROR_NO_SERVICE:
			 * Toast.makeText(getBaseContext(), "No service",
			 * Toast.LENGTH_SHORT).show(); break; case
			 * SmsManager.RESULT_ERROR_NULL_PDU:
			 * Toast.makeText(getBaseContext(), "Null PDU",
			 * Toast.LENGTH_SHORT).show(); break; case
			 * SmsManager.RESULT_ERROR_RADIO_OFF:
			 * Toast.makeText(getBaseContext(), "Radio off",
			 * Toast.LENGTH_SHORT).show(); break; } } }, new
			 * IntentFilter(SENT));
			 * 
			 * //---when the SMS has been delivered--- registerReceiver(new
			 * BroadcastReceiver(){
			 * 
			 * @Override public void onReceive(Context arg0, Intent arg1) {
			 * switch (getResultCode()) { case Activity.RESULT_OK:
			 * Toast.makeText(getBaseContext(), "SMS delivered",
			 * Toast.LENGTH_SHORT).show(); break; case Activity.RESULT_CANCELED:
			 * Toast.makeText(getBaseContext(), "SMS not delivered",
			 * Toast.LENGTH_SHORT).show(); break; } } }, new
			 * IntentFilter(DELIVERED));
			 * 
			 * SmsManager sms = SmsManager.getDefault();
			 * sms.sendTextMessage(phoneNumber, null, message, sentPI,
			 * deliveredPI); }
			 */

}
