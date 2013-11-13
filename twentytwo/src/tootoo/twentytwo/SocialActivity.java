package tootoo.twentytwo;

import java.util.ArrayList;
import java.util.List;

import tootoo.twentytwo.TweetDBContract.TweetEntry;
import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationBuilder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class SocialActivity extends Activity{
    static String TWITTER_CONSUMER_KEY = "STUFFFFFF";
    static String TWITTER_CONSUMER_SECRET = "Stufffffffffffff";
    
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        
        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        // TODO I have no idea how this will work
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey("Rtqg9HbxzxVb9Sp7T1Q").setOAuthConsumerSecret("zsMheHZcCIrVyLxDv3be9ocQ1D9XRDkyjVVBGkZVA").setOAuthAccessToken("**************************************************").setOAuthAccessTokenSecret("******************************************");
        AsyncTwitter twitter = new AsyncTwitterFactory(cb.build()).getInstance();
        try
        {
            List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("Showing home timeline.");
            for(Status status : statuses)
            {
                System.out.println(status.getUser().getName() + ":" + status.getText());
            }
            twitter.setOAuthConsumer("Rtqg9HbxzxVb9Sp7T1Q", "zsMheHZcCIrVyLxDv3be9ocQ1D9XRDkyjVVBGkZVA");
            
            // AccessToken accessToken = twitter.getOAuthAccessToken();
            // prefs.edit().putString("accessToken",
            // accessToken.getToken()).putString("accessTokenSecret",
            // accessToken.getTokenSecret()).commit();
        }catch(TwitterException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Log.d("AccessToken", prefs.getString("accessToken", "null"));
        
        TweetDBHelper helper = new TweetDBHelper(getBaseContext());
        SQLiteDatabase database = helper.getWritableDatabase();
        helper.onUpgrade(database, 0, 0);
        
        ContentValues values = new ContentValues();
        
        values.put(TweetEntry.COLUMN_NAME_USER_NAME, "Useruser 1");
        values.put(TweetEntry.COLUMN_NAME_TWEET_CONTENT, "hey hey hey hey hey hye hockey hockey problems yes");
        
        @SuppressWarnings("unused")
        long newRowId = database.insert(TweetEntry.TABLE_NAME, null, values);
        
        ArrayList<TwitterItem> twitterItem = new ArrayList<TwitterItem>();
        SQLiteCursor cursor = (SQLiteCursor) database.rawQuery("SELECT * FROM " + TweetEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            twitterItem.add(new TwitterItem(R.drawable.ic_launcher, cursor.getString(cursor.getColumnIndex(TweetEntry.COLUMN_NAME_USER_NAME)), cursor.getString(cursor.getColumnIndex(TweetEntry.COLUMN_NAME_TWEET_CONTENT))));
            cursor.moveToNext();
        }
        
        twitterItem.add(new TwitterItem(R.drawable.ic_launcher, "User 1", "1 Lorem Ipsum Dolor Sit Amet"));
        twitterItem.add(new TwitterItem(R.drawable.ic_launcher, "Person 2", "2 Lorem Ipsum Dolor Sit Amet"));
        
        TwitterItemAdapter adapter = new TwitterItemAdapter(this, R.layout.tweet_row, twitterItem);
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
