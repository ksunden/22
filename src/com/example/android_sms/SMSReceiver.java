package com.example.android_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver{
    
    @Override
    public void onReceive(Context context, Intent intent){
        if(DrivingActivity.isActive)
        {
            Bundle bundle = intent.getExtras();
            Object[] messages = (Object[]) bundle.get("pdus");
            SmsMessage[] sms = new SmsMessage[messages.length];
            // Create messages for each incoming PDU
            for(int n = 0; n < messages.length; n++)
            {
                sms[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
            }
            DrivingActivity.didRecieveMessage(sms);
        }
    }
    
}
