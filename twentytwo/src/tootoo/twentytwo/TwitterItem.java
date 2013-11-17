package tootoo.twentytwo;

// TODO Twitter Item class description
/**
 * 
 * @author Natalie Davenport
 * @version 11/17/2013
 */
public class TwitterItem{
    
    public int tweetImage;
    public String tweetUserName;
    public String tweetContent;
    
    public TwitterItem()
    {
        super();
    }
    
    public TwitterItem(int image, String userName, String content)
    {
        tweetImage = image;
        tweetUserName = userName;
        tweetContent = content;
        
    }
    
}
