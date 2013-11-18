package tootoo.twentytwo;

// TODO ListItem class description
/**
 * 
 * 
 * @author Natalie Davenport
 * @version 11/17/2013
 */
public class ListItem{
    
    public int image;
    public String title;
    public String desc;
    public String url;
    
    public ListItem()
    {
        super();
    }
    
    public ListItem(int orgImage, String orgTitle, String orgDesc, String address)
    {
        image = orgImage;
        title = orgTitle;
        desc = orgDesc;
        url = address;
    }
    
}
