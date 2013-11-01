package tootoo.twentytwo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class AboutUsActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        
        // TODO seperate file thing
        
        // TextView tv = (TextView) findViewById(R.id.bodyText1);
        
        // inner part of listview as a sepratate
        // use include fora seprate file
        // in java add subviews
        // have a database table
        // rather
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about_us, menu);
        return true;
    }
    
}
