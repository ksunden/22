package tootoo.twentytwo;

import android.provider.BaseColumns;

public class TweetDBContract{
    
    public TweetDBContract()
    {   
        
    }
    
    public static abstract class TweetEntry implements BaseColumns{
        
        public static final String TABLE_NAME = "tweets";
        public static final String COLUMN_NAME_USER_NAME = "username";
        public static final String COLUMN_NAME_TWEET_CONTENT = "tweetcontent";
    }
    
}
