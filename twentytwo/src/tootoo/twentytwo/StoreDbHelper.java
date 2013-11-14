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
    
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(StoreItems.SQL_CREATE_ITEMS);
        
        db.execSQL("INSERT INTO \"store_items\" VALUES (1, 'Wristbands (3 pack)', 10, null, 0);");
        db.execSQL("INSERT INTO \"store_items\" VALUES (2, 'Wristband', 5, null, 0);");
        db.execSQL("INSERT INTO \"store_items\" VALUES (3, 'Signed Puck', 22, null, 0);");
        db.execSQL("INSERT INTO \"store_items\" VALUES (4, 'Signed Jersey', 222, null, 0);");
        db.execSQL("INSERT INTO \"store_items\" VALUES (5, 'Hat', 15, null, 0);");
        
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(StoreItems.SQL_DELETE_ITEMS);
        onCreate(db);
    }
    
    public static int calculateTotal(SQLiteDatabase db){
        Cursor c = db.query(StoreItems.TABLE_NAME, new String[] {StoreItems._ID, StoreItems.COLUMN_NAME_PRICE, StoreItems.COLUMN_NAME_QUANTITY}, StoreItems.COLUMN_NAME_QUANTITY + ">0", null, null, null, null);
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
