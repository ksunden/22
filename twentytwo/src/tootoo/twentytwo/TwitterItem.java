package tootoo.twentytwo;

import java.io.IOException;

import android.graphics.Bitmap;

/**
 * After doing some research on custom ListViews, Kyle and I determined that
 * creating a simple TwitterItem object and its XML and then using a custom
 * adapter was the best way to go. This class holds the information for the
 * Twitter ListView in the SocialActivity class.
 * 
 * @author Natalie Davenport and Kyle Sunden
 * @version 11/17/2013
 */
public class TwitterItem{
    public Bitmap tweetImage;
    public String tweetUserName;
    public String tweetContent;
    public String tweetHandle;
    
    public TwitterItem()
    {
        super();
    }
    
    public TwitterItem(Bitmap image, String userName, String content, String handle) throws IOException
    {
        tweetImage = image;
        tweetUserName = userName;
        tweetContent = content;
        tweetHandle = handle;
    }
    
}
