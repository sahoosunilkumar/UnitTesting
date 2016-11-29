package com.example.sampleandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OutgoingCallReceiver extends BroadcastReceiver 
{
    @Override
    public void onReceive(Context xiContext, Intent xiIntent) 
    {
        if (xiIntent.getAction().equalsIgnoreCase(Intent.ACTION_NEW_OUTGOING_CALL))
        {
            String phoneNum = xiIntent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Log.i("TAG", "Outgoing call received~~~~"+phoneNum);
            Intent intent = new Intent(xiContext, MainActivity.class);
            intent.putExtra("phoneNum", phoneNum);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            xiContext.startActivity(intent);
//            setResultData(null);
        }
    }
}
