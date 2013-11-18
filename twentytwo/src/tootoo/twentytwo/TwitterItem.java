package tootoo.twentytwo;

import java.io.IOException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

// TODO Twitter Item class description
/**
 * 
 * @author Natalie Davenport
 * @version 11/17/2013
 */
public class TwitterItem{
    public Bitmap tweetImage;
    // public int tweetImageLoc;
    public String tweetUserName;
    public String tweetContent;
    public String tweetHandle;
    
    // TODO problems with image
    
    public TwitterItem()
    {
        super();
    }
    
    // String
    public TwitterItem(String address, String userName, String content, String handle) throws IOException
    {
        URL tweetImageURL = new URL(address);
        tweetImage = BitmapFactory.decodeStream(tweetImageURL.openConnection().getInputStream());
        tweetUserName = userName;
        tweetContent = content;
        tweetHandle = handle;
    }
    
}
