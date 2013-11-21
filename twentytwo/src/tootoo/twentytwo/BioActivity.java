package tootoo.twentytwo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * This activity contains sample content regardin Jordin's story that will be
 * replaced at a later date.
 * 
 * @author Natalie Davenport and Kyle Sunden
 * @version 11/17/2013
 */
public class BioActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bio, menu);
        return true;
    }
    
}
