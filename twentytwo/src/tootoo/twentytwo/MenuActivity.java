package tootoo.twentytwo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity{
    Context mContext = this;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        // TODO Add image decode/boundaries stuff
        
        // Creates an intent to transfer the user to the info and bio screen.
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
        
        Button socialButton = (Button) findViewById(R.id.socialButton);
        socialButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mContext, BioActivity.class);
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
        
        Button resourcesButton = (Button) findViewById(R.id.resourcesButton);
        resourcesButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mContext, BioActivity.class);
                startActivity(intent);
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    
}
