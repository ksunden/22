package tootoo.twentytwo;

import java.util.ArrayList;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterMethod;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class SocialActivity extends Activity{
    static String TWITTER_CONSUMER_KEY = "Rtqg9HbxzxVb9Sp7T1Q";
    static String TWITTER_CONSUMER_SECRET = "zsMheHZcCIrVyLxDv3be9ocQ1D9XRDkyjVVBGkZVA";
    
    private static ArrayList<TwitterItem> twitterList;
    private static TwitterItemAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        
        @SuppressWarnings("unused")
        SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(TWITTER_CONSUMER_KEY).setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET).setOAuthAccessToken("1117933645-5KR4AJIvfQ9xvN0YTX28cJNoalk725VMFdOVLJg").setOAuthAccessTokenSecret("MgLqOiDqyMpYcQ53PE2Zg0paNWsuAxYilNREKfpIIqFqV");
        AsyncTwitter twitter = new AsyncTwitterFactory(cb.build()).getInstance();
        
        twitter.addListener(new TwitterAdapter() {
            @Override
            public void gotUserTimeline(ResponseList<Status> statuses){
                Log.d("I AM HERE", ":) HI FRIEND");
                
                for(Status status : statuses)
                {
                    
                    System.out.println(status.getUser().getName() + ":" + status.getText());
                    TwitterItem item = new TwitterItem(R.drawable.ic_launcher, status.getUser().getName(), status.getText());
                    // twitterList.add(item);
                    adapter.add(item);
                }
                adapter.notifyDataSetChanged();
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
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        twitterList = new ArrayList<TwitterItem>();
        adapter = new TwitterItemAdapter(this, R.layout.tweet_row, twitterList);
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
