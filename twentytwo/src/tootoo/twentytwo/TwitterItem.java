package tootoo.twentytwo;

import java.io.IOException;

import android.graphics.Bitmap;

// TODO Twitter Item class description
/**
 * 
 * @author Natalie Davenport
 * @version 11/17/2013
 */
public class TwitterItem{
    public Bitmap tweetImage;
    public String tweetUserName;
    public String tweetContent;
    public String tweetHandle;
    /*public String tweetDate;*/
    
    public TwitterItem()
    {
        super();
    }
    
    public TwitterItem(Bitmap image, String userName, String content, String handle/*, String date*/) throws IOException
    {
        tweetImage = image;
        tweetUserName = userName;
        tweetContent = content;
        tweetHandle = handle;
        /*tweetDate = date;*/
    }
    
}
