package tootoo.twentytwo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class AboutUsActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        
        TextView tv = (TextView) findViewById(R.id.bodyText1);
        tv.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam gravida tristique nunc, ut porta justo imperdiet nec. Suspendisse potenti. Morbi lorem massa, luctus sit amet sem non, vulputate vestibulum metus. Phasellus consequat at sem non auctor. Duis porttitor, tellus in pharetra dapibus, tortor est commodo libero, vitae tempus urna metus sit amet massa. Donec suscipit ultricies tincidunt. Cras sit amet auctor ipsum, non pharetra diam.");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about_us, menu);
        return true;
    }
    
}
