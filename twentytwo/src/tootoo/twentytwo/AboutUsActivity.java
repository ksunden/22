package tootoo.twentytwo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class AboutUsActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        
        ListItem list_item_data[] = new ListItem[] {new ListItem(R.drawable.ic_launcher, "Org 1", "1 Lorem Ipsum Dolor Sit Amet"), new ListItem(R.drawable.ic_launcher, "Org 2", "2 Lorem Ipsum Dolor Sit Amet"), new ListItem(R.drawable.ic_launcher, "Org 3", "3 Lorem Ipsum Dolor Sit Amet"), new ListItem(R.drawable.ic_launcher, "Org 4", "4 Lorem Ipsum Dolor Sit Amet")};
        
        ListItemAdapter adapter = new ListItemAdapter(this, R.layout.about_us_row, list_item_data);
        ListView lv = (ListView) findViewById(R.id.aboutUsListView);
        lv.setAdapter(adapter);
        
        // TODO Make the ListItems clickable
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about_us, menu);
        return true;
    }
    
}
