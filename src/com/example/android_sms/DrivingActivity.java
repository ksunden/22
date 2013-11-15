package com.example.android_sms;

import java.util.Locale;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.v4.app.NavUtils;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * DrivingActivity is an activity for the 'No Texting While Driving' app while
 * the app is active. From this activity, incoming texts can be read to the user
 * without displaying the text on the screen. The user can repeat the most
 * recent text via a large button on the screen.
 * 
 * @author Kyle Sunden and Natalie Davenport
 * @version 11/15/2013
 */

public class DrivingActivity extends Activity implements OnInitListener{
    
    // Tracks if no texting while driving is active
    public static boolean isActive = false;
    
    // Information about the last message
    private static String lastText = "";
    private static String lastNumber = "";
    
    private static TextToSpeech tts;
    private static SmsManager sms;
    
    private DrivingActivity mContext;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_driving);
        
        // Setup managing objects
        tts = new TextToSpeech(mContext, mContext);
        sms = SmsManager.getDefault();
        
        // Use button to repeat the last received message
        Button repeat = (Button) findViewById(R.id.repeatMessage);
        repeat.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v){
                speak();
            }
        });
        
        // Use check box to track text to speech preference
        CheckBox textToSpeech = (CheckBox) findViewById(R.id.speechEnabled1);
        textToSpeech.setChecked(MainActivity.isSpeechEnabled);
        textToSpeech.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                MainActivity.isSpeechEnabled = isChecked;
            }
        });
        
        // Show the Up button in the action bar.
        setupActionBar();
    }
    
    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    
    // Auto generated options menu methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.driving, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId())
        {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public static void didRecieveMessage(SmsMessage[] msgs){
        // Concatenate long message to one
        lastNumber = msgs[0].getOriginatingAddress();
        lastText = "";
        for(SmsMessage msg : msgs)
        {
            lastText += msg.getDisplayMessageBody();
        }
        
        // Speak message
        if(MainActivity.isSpeechEnabled)
        {
            speak();
        }
        // Respond to the message
        sms.sendTextMessage(lastNumber, null, MainActivity.responseText, null, null);
    }
    
    // Speak the most recent text
    private static void speak(){
        tts.speak("Message from " + lastNumber + " " + lastText, TextToSpeech.QUEUE_ADD, null);
    }
    
    // Initialize the text to speech
    @Override
    public void onInit(int status){
        
        if(status == TextToSpeech.SUCCESS)
        {
            
            int result = tts.setLanguage(Locale.US);
            
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("TTS", "This Language is not supported");
            }
            
        }else
        {
            Log.e("TTS", "Initilization Failed!");
        }
    }
    
    // Stop text to speech and set boolean.
    @Override
    protected void onDestroy(){
        if(tts != null)
        {
            tts.stop();
            tts.shutdown();
        }
        
        isActive = false;
        super.onDestroy();
    }
    
    // Set tracking as active
    @Override
    protected void onResume(){
        isActive = true;
        super.onResume();
        
    }
    
}
