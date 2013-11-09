package tootoo.twentytwo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class SocialActivity extends Activity{
    
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        
        TwitterItem twitter_item_data[] = new TwitterItem[] {new TwitterItem(R.drawable.ic_launcher, "User 1", "1 Lorem Ipsum Dolor Sit Amet"), new TwitterItem(R.drawable.ic_launcher, "Person 2", "2 Lorem Ipsum Dolor Sit Amet")};
        TwitterItemAdapter adapter = new TwitterItemAdapter(this, R.layout.tweet_row, twitter_item_data);
        ListView lv = (ListView) findViewById(R.id.twitterListView);
        lv.setAdapter(adapter);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.social, menu);
        return true;
    }
}
