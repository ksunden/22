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
    public String tweetUserName;
    public String tweetContent;
    public String tweetHandle;
    /*public String tweetDate;*/
    
    public TwitterItem()
    {
        super();
    }
    
    public TwitterItem(String address, String userName, String content, String handle/*, String date*/) throws IOException
    {
        URL tweetImageURL = new URL(address);
        Bitmap unscaledBitmap = BitmapFactory.decodeStream(tweetImageURL.openConnection().getInputStream());
        tweetImage = Bitmap.createScaledBitmap(unscaledBitmap, 50, 50, true);
        tweetUserName = userName;
        tweetContent = content;
        tweetHandle = handle;
        /*tweetDate = date;*/
    }
    
}
