package tootoo.twentytwo;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

// TODO Social Activity class description
/**
 * 
 * 
 * @author Kyle Sunden and Natalie Davenport
 * @version 11/17/2013
 */
public class SocialActivity extends Activity{
    private static final String TWITTER_OAUTH_ACCESS_TOKEN_SECRET = "MgLqOiDqyMpYcQ53PE2Zg0paNWsuAxYilNREKfpIIqFqV";
    private static final String TWITTER_OAUTH_ACCESS_TOKEN = "1117933645-5KR4AJIvfQ9xvN0YTX28cJNoalk725VMFdOVLJg";
    private static final String TWITTER_CONSUMER_KEY = "Rtqg9HbxzxVb9Sp7T1Q";
    private static final String TWITTER_CONSUMER_SECRET = "zsMheHZcCIrVyLxDv3be9ocQ1D9XRDkyjVVBGkZVA";
    
    private static volatile ArrayList<TwitterItem> twitterList;
    private static volatile TwitterItemAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle("Social Media");
        setContentView(R.layout.activity_social);
        
        // Create twitter object to interact with twitter
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(TWITTER_CONSUMER_KEY).setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET).setOAuthAccessToken(TWITTER_OAUTH_ACCESS_TOKEN).setOAuthAccessTokenSecret(TWITTER_OAUTH_ACCESS_TOKEN_SECRET);
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        
        // Retrieve tweets from twitter
        twitterList = new ArrayList<TwitterItem>();
        adapter = new TwitterItemAdapter(this, R.layout.tweet_row, twitterList);
        new GetTweets().execute(twitter);
        
        // Adapt tweets to the list
        ListView lv = (ListView) findViewById(R.id.twitterListView);
        lv.setAdapter(adapter);
        
        // Tweet @TeamTootooFund
        final EditText tweetAt = (EditText) findViewById(R.id.social_tweetAt);
        Button postButton = (Button) findViewById(R.id.social_submit);
        postButton.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v){
                // Encode text written in the app
                String text = "";
                try
                {
                    text = URLEncoder.encode(tweetAt.getText().toString(), "UTF-8");
                }catch(UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                
                // Open in browser to sign in and post to twitter
                String url = "https://twitter.com/intent/tweet?screen_name=TeamTootooFund&text=" + text;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.social, menu);
        return true;
    }
    
    // Task to retrieve tweets from @TeamTootooFund in a background thread
    public class GetTweets extends AsyncTask<Twitter, Void, ArrayList<TwitterItem>>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }
        
        @Override
        protected ArrayList<TwitterItem> doInBackground(Twitter... params){
            try
            {
                // Retrieve the tweets and add them to an ArrayList
                ResponseList<twitter4j.Status> statuses = params[0].getUserTimeline("@TeamTootooFund");
                ArrayList<TwitterItem> list = new ArrayList<TwitterItem>();
                
                // HashMap for user profile pictures prevents multiple bitmaps
                // for one user
                HashMap<String, Bitmap> userImages = new HashMap<String, Bitmap>();
                for(twitter4j.Status status : statuses)
                {
                    // Test if status is a retweet
                    if(status.isRetweet()) status = status.getRetweetedStatus();
                    
                    // Retrieve the username of the person who posted this
                    // status
                    String screenName = "@" + status.getUser().getScreenName();
                    if(!userImages.containsKey(screenName))
                    {
                        // Retrieve user profile picture, if not found, or any
                        // other exception, use the application icon
                        try
                        {
                            userImages.put(screenName, BitmapFactory.decodeStream(new URL(status.getUser().getBiggerProfileImageURL()).openStream()));
                        }catch(Exception e)
                        {
                            e.printStackTrace();
                            userImages.put(screenName, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
                        }
                    }
                    
                    // Add to list
                    TwitterItem item = new TwitterItem(userImages.get(screenName), status.getUser().getName(), status.getText(), screenName);
                    list.add(item);
                }
                return list;
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            return null;
            
        }
        
        // Update the displayed list
        @Override
        protected void onPostExecute(ArrayList<TwitterItem> result){
            if(result != null)
            {
                adapter.addAll(result);
                adapter.notifyDataSetChanged();
            }
            super.onPostExecute(result);
        }
        
    }
}
