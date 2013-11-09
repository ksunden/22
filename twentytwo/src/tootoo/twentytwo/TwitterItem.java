package tootoo.twentytwo;

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
