package tootoo.twentytwo;

import tootoo.twentytwo.TweetDBContract.TweetEntry;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TweetDBHelper extends SQLiteOpenHelper{
    // If you change the database schema, you must increment the database
    // version.
    public static final int DATABASE_VERSION = 1;
    
    public static final String DATABASE_NAME = "TooToo.db";
    
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TweetEntry.TABLE_NAME + " (" + TweetEntry._ID + " INTEGER PRIMARY KEY," + TweetEntry.COLUMN_NAME_USER_NAME + TEXT_TYPE + COMMA_SEP + TweetEntry.COLUMN_NAME_TWEET_CONTENT + TEXT_TYPE + " )";
    
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TweetEntry.TABLE_NAME;
    
    public TweetDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // This database is only a cache for online data, so its upgrade policy
        // is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}