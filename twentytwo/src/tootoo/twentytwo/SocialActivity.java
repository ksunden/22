package tootoo.twentytwo;

import java.util.ArrayList;
import java.util.Map;

import tootoo.twentytwo.TweetDBContract.TweetEntry;
import twitter4j.AccountSettings;
import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.Category;
import twitter4j.DirectMessage;
import twitter4j.Friendship;
import twitter4j.IDs;
import twitter4j.Location;
import twitter4j.OEmbed;
import twitter4j.PagableResponseList;
import twitter4j.Place;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Relationship;
import twitter4j.ResponseList;
import twitter4j.SavedSearch;
import twitter4j.SimilarPlaces;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.TwitterAPIConfiguration;
import twitter4j.TwitterException;
import twitter4j.TwitterListener;
import twitter4j.TwitterMethod;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.api.HelpResources.Language;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
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
        twitter.addListener(new TwitterListener() {
            
            @Override
            public void verifiedCredentials(User arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void updatedUserList(UserList arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void updatedStatus(Status arg0){}
            
            @Override
            public void updatedProfileImage(User arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void updatedProfileColors(User arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void updatedProfileBanner(){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void updatedProfileBackgroundImage(User arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void updatedProfile(User arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void updatedFriendship(Relationship arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void updatedAccountSettings(AccountSettings arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void unsubscribedUserList(UserList arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void subscribedUserList(UserList arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void sentDirectMessage(DirectMessage arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void searchedUser(ResponseList<User> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void searchedPlaces(ResponseList<Place> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void searched(QueryResult arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void retweetedStatus(Status arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void reportedSpam(User arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void removedProfileBanner(){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void onException(TwitterException arg0, TwitterMethod arg1){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void lookedupUsers(ResponseList<User> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void lookedUpFriendships(ResponseList<Friendship> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotUserTimeline(ResponseList<Status> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotUserSuggestions(ResponseList<User> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotUserLists(ResponseList<UserList> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotUserListSubscriptions(PagableResponseList<UserList> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotUserListSubscribers(PagableResponseList<User> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotUserListStatuses(ResponseList<Status> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotUserListMemberships(PagableResponseList<UserList> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotUserListMembers(PagableResponseList<User> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotUserDetail(User arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotTermsOfService(String arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotSuggestedUserCategories(ResponseList<Category> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotSimilarPlaces(SimilarPlaces arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotShowUserList(UserList arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotShowStatus(Status arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotShowFriendship(Relationship arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotSentDirectMessages(ResponseList<DirectMessage> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotSavedSearches(ResponseList<SavedSearch> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotSavedSearch(SavedSearch arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotReverseGeoCode(ResponseList<Place> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotRetweetsOfMe(ResponseList<Status> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotRetweets(ResponseList<Status> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotRateLimitStatus(Map<String, RateLimitStatus> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotPrivacyPolicy(String arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotPlaceTrends(Trends arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotOutgoingFriendships(IDs arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotOEmbed(OEmbed arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotOAuthRequestToken(RequestToken arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotOAuthAccessToken(AccessToken arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotMentions(ResponseList<Status> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotMemberSuggestions(ResponseList<User> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotLanguages(ResponseList<Language> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotIncomingFriendships(IDs arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotHomeTimeline(ResponseList<Status> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotGeoDetails(Place arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotFriendsList(PagableResponseList<User> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotFriendsIDs(IDs arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotFollowersList(PagableResponseList<User> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotFollowersIDs(IDs arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotFavorites(ResponseList<Status> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotDirectMessages(ResponseList<DirectMessage> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotDirectMessage(DirectMessage arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotContributors(ResponseList<User> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotContributees(ResponseList<User> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotClosestTrends(ResponseList<Location> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotBlocksList(ResponseList<User> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotBlockIDs(IDs arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotAvailableTrends(ResponseList<Location> arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotAccountSettings(AccountSettings arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void gotAPIConfiguration(TwitterAPIConfiguration arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void destroyedUserListMember(UserList arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void destroyedUserList(UserList arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void destroyedStatus(Status arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void destroyedSavedSearch(SavedSearch arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void destroyedFriendship(User arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void destroyedFavorite(Status arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void destroyedDirectMessage(DirectMessage arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void destroyedBlock(User arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void createdUserListMembers(UserList arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void createdUserListMember(UserList arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void createdUserList(UserList arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void createdSavedSearch(SavedSearch arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void createdPlace(Place arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void createdFriendship(User arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void createdFavorite(Status arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void checkedUserListSubscription(User arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void checkedUserListMembership(User arg0){
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void createdBlock(User arg0){
                // TODO Auto-generated method stub
                
            }
        });
        try
        {
            // List<Status> statuses =
            twitter.getHomeTimeline();
            System.out.println("Showing home timeline.");
            // for(Status status : statuses)
            // {
            // System.out.println(status.getUser().getName() + ":" +
            // status.getText());
            // }
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
