package tootoo.twentytwo;

import java.util.ArrayList;

import tootoo.twentytwo.TweetDBContract.TweetEntry;
import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterMethod;
import twitter4j.conf.ConfigurationBuilder;
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
    static String TWITTER_CONSUMER_KEY = "Rtqg9HbxzxVb9Sp7T1Q";
    static String TWITTER_CONSUMER_SECRET = "zsMheHZcCIrVyLxDv3be9ocQ1D9XRDkyjVVBGkZVA";
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        
        @SuppressWarnings("unused")
        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(TWITTER_CONSUMER_KEY).setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET).setOAuthAccessToken("1117933645-5KR4AJIvfQ9xvN0YTX28cJNoalk725VMFdOVLJg").setOAuthAccessTokenSecret("MgLqOiDqyMpYcQ53PE2Zg0paNWsuAxYilNREKfpIIqFqV");
        AsyncTwitter twitter = new AsyncTwitterFactory(cb.build()).getInstance();
        
        TweetDBHelper helper = new TweetDBHelper(getBaseContext());
        SQLiteDatabase database = helper.getWritableDatabase();
        helper.onUpgrade(database, 0, 0);
        ContentValues values = new ContentValues();
        
        twitter.addListener(new TwitterAdapter() {
            @Override
            public void gotUserTimeline(ResponseList<Status> statuses){
                Log.d("I AM HERE", ":) HI FRIEND");
                
                for(Status status : statuses)
                {
                    
                    System.out.println(status.getUser().getName() + ":" + status.getText());
                    values.put(TweetEntry.COLUMN_NAME_USER_NAME, status.getUser().getName());
                    values.put(TweetEntry.COLUMN_NAME_TWEET_CONTENT, status.getText());
                    
                    @SuppressWarnings("unused")
                    long newRowId = database.insert(TweetEntry.TABLE_NAME, null, values);
                }
                super.gotUserTimeline(statuses);
            }
            
            @Override
            public void onException(TwitterException te, TwitterMethod method){
                te.printStackTrace();
                super.onException(te, method);
            }
            
        });
        
        try
        {
            twitter.getOAuthAccessToken();
            twitter.getUserTimeline("@TeamTootooFund");
            // for(Status status : statuses)
            // {
            // System.out.println(status.getUser().getName() + ":" +
            // status.getText());
            // }
            // twitter.setOAuthConsumer("Rtqg9HbxzxVb9Sp7T1Q",
            // "zsMheHZcCIrVyLxDv3be9ocQ1D9XRDkyjVVBGkZVA");
            
            // AccessToken accessToken = twitter.getOAuthAccessToken();
            // prefs.edit().putString("accessToken",
            // accessToken.getToken()).putString("accessTokenSecret",
            // accessToken.getTokenSecret()).commit();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
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
        
        database.close();
        cursor.close();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.social, menu);
        return true;
    }
}
