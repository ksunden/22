package tootoo.twentytwo;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

// TODO Social Activity class description
/**
 * 
 * 
 * @author Kyle Sunden and Natalie Davenport
 * @version 11/17/2013
 */
public class SocialActivity extends Activity{
    static String TWITTER_CONSUMER_KEY = "Rtqg9HbxzxVb9Sp7T1Q";
    static String TWITTER_CONSUMER_SECRET = "zsMheHZcCIrVyLxDv3be9ocQ1D9XRDkyjVVBGkZVA";
    
    private static volatile ArrayList<TwitterItem> twitterList;
    private static volatile TwitterItemAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle("Social Media");
        setContentView(R.layout.activity_social);
        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(TWITTER_CONSUMER_KEY).setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET).setOAuthAccessToken("1117933645-5KR4AJIvfQ9xvN0YTX28cJNoalk725VMFdOVLJg").setOAuthAccessTokenSecret("MgLqOiDqyMpYcQ53PE2Zg0paNWsuAxYilNREKfpIIqFqV");
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        
        twitterList = new ArrayList<TwitterItem>();
        adapter = new TwitterItemAdapter(this, R.layout.tweet_row, twitterList);
        new GetTweets().execute(twitter);
        
        ListView lv = (ListView) findViewById(R.id.twitterListView);
        lv.setAdapter(adapter);
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
                HashMap<String, Bitmap> userImages = new HashMap<String, Bitmap>();
                for(twitter4j.Status status : statuses)
                {
                    
                    if(status.isRetweet()) status = status.getRetweetedStatus();
                    String screenName = "@" + status.getUser().getScreenName();
                    System.out.println(status.getUser().getName() + " " + screenName + ":" + status.getText());
                    if(!userImages.containsKey(screenName))
                    {
                        BitmapFactory.Options opts = new BitmapFactory.Options();
                        int size = 10;
                        opts.outHeight = size;
                        opts.outWidth = size;
                        userImages.put(screenName, BitmapFactory.decodeStream(new URL(status.getUser().getBiggerProfileImageURL()).openStream(), null, opts));
                        Log.d("Image Stuff", userImages.get(screenName).toString());
                    }
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
            adapter.addAll(result);
            adapter.notifyDataSetChanged();
            super.onPostExecute(result);
        }
        
    }
}
