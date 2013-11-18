package tootoo.twentytwo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

// TODO About Us Activity class description
/**
 * 
 * @author Kyle Sunden and Natalie Davenport
 * @version 11/17/2013
 */
public class AboutUsActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle("About Team Tootoo");
        setContentView(R.layout.activity_about_us);
        
        // Set up an ArrayList to hold the ListItem data for the ListView, and
        // add the data
        ArrayList<ListItem> listItemData = new ArrayList<ListItem>();
        listItemData.add(new ListItem(R.drawable.ic_launcher, "Org 1", "1 Lorem Ipsum Dolor Sit Amet", "http://www.google.com"));
        listItemData.add(new ListItem(R.drawable.ic_launcher, "Org 2", "2 Lorem Ipsum Dolor Sit Amet", "http://www.yahoo.com"));
        listItemData.add(new ListItem(R.drawable.ic_launcher, "Org 3", "3 Lorem Ipsum Dolor Sit Amet", "http://www.google.com"));
        listItemData.add(new ListItem(R.drawable.ic_launcher, "Org 4", "4 Lorem Ipsum Dolor Sit Amet", "http://www.google.com"));
        
        // Set up the adapter for the ListView
        ListItemAdapter adapter = new ListItemAdapter(this, R.layout.about_us_row, listItemData);
        ListView lv = (ListView) findViewById(R.id.aboutUsListView);
        lv.setAdapter(adapter);
        
        // Make each ListItem in the ListView clickable, and send the user to
        // each organization's webpage
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                ListItem li = (ListItem) arg0.getItemAtPosition(arg2);
                Intent linkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(li.url));
                startActivity(linkIntent);
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.about_us, menu);
        return true;
    }
}
