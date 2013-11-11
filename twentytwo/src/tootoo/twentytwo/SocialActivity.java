package tootoo.twentytwo;

import java.util.ArrayList;

import tootoo.twentytwo.TweetDBContract.TweetEntry;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
        
        // TODO I have no idea how this will work and won't be able to get it to
        // work for some time yet
        
        /*
         * Twitter twitter = TwitterFactory.getSingleton();
         * twitter.setOAuthConsumer(TWITTER_CONSUMER_KEY,
         * TWITTER_CONSUMER_SECRET); List<Status> statuses = null; try {
         * statuses = twitter.getUserTimeline("TeamTooTooFund");
         * }catch(TwitterException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); } System.out.println("Showing home timeline.");
         * for(Status status : statuses) {
         * System.out.println(status.getUser().getName() + ":" +
         * status.getText()); }
         */
        
        TweetDBHelper helper = new TweetDBHelper(getBaseContext());
        SQLiteDatabase database = helper.getWritableDatabase();
        helper.onUpgrade(database, 0, 0);
        
        ContentValues values = new ContentValues();
        
        values.put(TweetEntry.COLUMN_NAME_USER_NAME, "Useruser 1");
        values.put(TweetEntry.COLUMN_NAME_TWEET_CONTENT, "hey hey hey hey hey hye hockey hockey problems yes");
        
        @SuppressWarnings("unused")
        long newRowId = database.insert(TweetEntry.TABLE_NAME, null, values);
        
        ArrayList<TwitterItem> twitterItem = new ArrayList<TwitterItem>();
        // Cursor cursor = database.rawQuery("SELECT * FROM " +
        // TweetEntry.TABLE_NAME, null);
        // while(!cursor.isAfterLast())
        // {
        // twitterItem.add(new TwitterItem(R.drawable.ic_launcher,
        // cursor.getString(cursor.getColumnIndex(TweetEntry.COLUMN_NAME_USER_NAME)),
        // cursor.getString(cursor.getColumnIndex(TweetEntry.COLUMN_NAME_TWEET_CONTENT))));
        // }
        
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
