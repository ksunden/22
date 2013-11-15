package com.example.android_sms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity{
    private Context mContext = this;
    public static boolean isSpeechEnabled;
    public static String responseText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        isSpeechEnabled = false;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        final EditText message = (EditText) findViewById(R.id.message);
        message.setText("I'm currently driving and cannot answer your text");
        
        Button driveButton = (Button) findViewById(R.id.startDriving);
        driveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                responseText = message.getText().toString();
                Intent intent = new Intent(mContext, DrivingActivity.class);
                startActivity(intent);
            }
        });
        
        CheckBox textToSpeach = (CheckBox) findViewById(R.id.speechEnabled1);
        textToSpeach.setChecked(MainActivity.isSpeechEnabled);
        textToSpeach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                MainActivity.isSpeechEnabled = isChecked;
            }
        });
        
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(checkedId == R.id.radioDriving)
                {
                    message.setText("I'm currently driving and cannot answer your text");
                }else if(checkedId == R.id.radioOffice)
                {
                    message.setText("I'm currently working and cannot answer your text");
                }else if(checkedId == R.id.radioMeeting)
                {
                    message.setText("I'm currently in a meeting and cannot answer your text");
                }else
                {
                    message.setText("");
                    message.setHint("Enter your own message here");
                }
            }
        });
    }
    
    @Override
    protected void onResume(){
        CheckBox textToSpeach = (CheckBox) findViewById(R.id.speechEnabled1);
        textToSpeach.setChecked(MainActivity.isSpeechEnabled);
        super.onRestart();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
