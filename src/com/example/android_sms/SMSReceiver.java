package com.example.android_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * SMSReceiver is the BroadcastReceiver for the 'No Texting While Driving' app.
 * This class intercepts incoming messages and passes the data to the driving
 * activity.
 * 
 * @author Kyle Sunden and Natalie Davenport
 * @version 11/15/2013
 */

public class SMSReceiver extends BroadcastReceiver{
    
    @Override
    public void onReceive(Context context, Intent intent){
        // Only handle if the app is active
        if(DrivingActivity.isActive)
        {
            // Retrieve data
            Bundle bundle = intent.getExtras();
            Object[] messages = (Object[]) bundle.get("pdus");
            SmsMessage[] sms = new SmsMessage[messages.length];
            
            // Create messages for each incoming PDU
            for(int n = 0; n < messages.length; n++)
            {
                sms[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
            }
            
            // Call method for handling the data from incoming mesages
            DrivingActivity.didRecieveMessage(sms);
        }
    }
    
}
