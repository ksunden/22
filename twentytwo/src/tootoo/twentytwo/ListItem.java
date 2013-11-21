package tootoo.twentytwo;

/**
 * After doing some research on custom ListViews, Kyle and I determined that
 * creating a simple ListItem object and its XML and then using a custom adapter
 * was the best way to go. This class holds the information for the elements in
 * the Supported Organizations ListView.
 * 
 * @author Natalie Davenport and Kyle Sunden
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
