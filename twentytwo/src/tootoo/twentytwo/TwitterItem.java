package tootoo.twentytwo;

// TODO Twitter Item class description
/**
 * 
 * @author Natalie Davenport
 * @version 11/17/2013
 */
public class TwitterItem{
    
    public int tweetImageLoc;
    public String tweetUserName;
    public String tweetContent;
    public String tweetHandle;
    
    // TODO problems with image
    
    public TwitterItem()
    {
        super();
    }
    
    public TwitterItem(int address, String userName, String content, String handle)
    {
        tweetImageLoc = address;
        tweetUserName = userName;
        tweetContent = content;
        tweetHandle = handle;
    }
    
}
