package tootoo.twentytwo;

import android.provider.BaseColumns;

public class StoreContract{
    
    public StoreContract()
    {}
    
    // Define the column headings for a table of Team Tootoo items
    public static abstract class StoreItems implements BaseColumns{
        public static final String TABLE_NAME = "store_items";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_IMAGE_LOCATION = "image_location";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
        
        // String to create table
        public static String SQL_CREATE_ITEMS = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME_NAME + " TEXT," + COLUMN_NAME_PRICE + " INTEGER," + COLUMN_NAME_IMAGE_LOCATION + " INTEGER," + COLUMN_NAME_QUANTITY + " INTEGER)";
        
        // String to delete table
        public static final String SQL_DELETE_ITEMS = "DROP TABLE IF EXISTS " + TABLE_NAME;
        
    }
    
}
