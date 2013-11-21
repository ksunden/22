package tootoo.twentytwo;

import tootoo.twentytwo.StoreContract.StoreItems;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreDbHelper extends SQLiteOpenHelper{
    
    // If you change the database schema, you must increment the database
    // version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tootoo.db";
    
    public StoreDbHelper(Context c)
    {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Create the database
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(StoreItems.SQL_CREATE_ITEMS);
        
        // TODO reformat database insertions
        db.execSQL("INSERT INTO \"" + StoreItems.TABLE_NAME + "\" VALUES (1, 'Wristbands\n(3 Pack)', 10, " + R.drawable.wristband3 + ", 0);");
        db.execSQL("INSERT INTO \"" + StoreItems.TABLE_NAME + "\" VALUES (2, 'Wristband\n', 5, " + R.drawable.wristband1 + ", 0);");
        db.execSQL("INSERT INTO \"" + StoreItems.TABLE_NAME + "\" VALUES (3, 'Signed Puck', 22, " + R.drawable.puck + ", 0);");
        db.execSQL("INSERT INTO \"" + StoreItems.TABLE_NAME + "\" VALUES (4, 'Signed Jersey', 222, " + R.drawable.jersey + ", 0);");
        db.execSQL("INSERT INTO \"" + StoreItems.TABLE_NAME + "\" VALUES (5, 'Hat', 15, " + R.drawable.no_sign_cap + ", 0);");
        db.execSQL("INSERT INTO \"" + StoreItems.TABLE_NAME + "\" VALUES (6, 'Signed Hat', 22, " + R.drawable.sign_cap + ", 0);");
        
    }
    
    // Delete and recreate the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(StoreItems.SQL_DELETE_ITEMS);
        onCreate(db);
    }
    
    // Calculate and return the total of the items in the database
    public static int calculateTotal(SQLiteDatabase db){
        // Retrieve only items with positive quantity
        Cursor c = db.query(StoreItems.TABLE_NAME, new String[] {StoreItems._ID, StoreItems.COLUMN_NAME_PRICE, StoreItems.COLUMN_NAME_QUANTITY}, StoreItems.COLUMN_NAME_QUANTITY + ">0", null, null, null, null);
        
        // Add all items to total
        c.moveToFirst();
        int sum = 0;
        while(!c.isAfterLast())
        {
            sum += c.getInt(c.getColumnIndex(StoreItems.COLUMN_NAME_QUANTITY)) * c.getInt(c.getColumnIndex(StoreItems.COLUMN_NAME_PRICE));
            c.moveToNext();
        }
        return sum;
    }
    
}
