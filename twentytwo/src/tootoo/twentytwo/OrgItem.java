package tootoo.twentytwo;

public class OrgItem{
    
    private String orgImageFile;
    private String orgName;
    private String orgDesc;
    
    public OrgItem(String imagePath, String title, String desc)
    {
        orgImageFile = imagePath;
        orgName = title;
        orgDesc = desc;
    }
}
