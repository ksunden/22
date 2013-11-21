package tootoo.twentytwo;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * The MenuActivity class holds the button listeners for links to various pages.
 * 
 * @author Kyle Sunden and Natalie Davenport
 * @version 11/17/2013
 */
public class MenuActivity extends Activity{
    Context mContext = this;
    
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        
        // Creates an intent to transfer the user to Tootoo's bio screen.
        Button bioButton = (Button) findViewById(R.id.bioButton);
        bioButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mContext, BioActivity.class);
                startActivity(intent);
            }
        });
        
        // Creates an intent to transfer the user to the store activity.
        Button storeButton = (Button) findViewById(R.id.storeButton);
        storeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mContext, StoreActivity.class);
                startActivity(intent);
            }
        });
        
        // Creates an intent to transfer the user to the social media activity.
        Button socialButton = (Button) findViewById(R.id.socialButton);
        socialButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mContext, SocialActivity.class);
                startActivity(intent);
            }
        });
        
        // Creates an intent to transfer the user to the 'about us' activity.
        Button aboutUsButton = (Button) findViewById(R.id.aboutUsButton);
        aboutUsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mContext, AboutUsActivity.class);
                startActivity(intent);
            }
        });
    }
}
