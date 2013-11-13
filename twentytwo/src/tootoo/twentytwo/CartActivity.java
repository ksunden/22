package tootoo.twentytwo;

import tootoo.twentytwo.StoreContract.StoreItems;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.SimpleCursorAdapter.ViewBinder;

public class CartActivity extends Activity{
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        
        SQLiteDatabase db = new StoreDbHelper(this).getWritableDatabase();
        Cursor cursor = db.query(StoreItems.TABLE_NAME,null, null, null, null, null, null);
        ListView list = (ListView) findViewById(R.id.cart_listview);
        SimpleCursorAdapter itemAdapter = new SimpleCursorAdapter(this, R.layout.item_detail, cursor, new String[]
                {StoreItems.COLUMN_NAME_NAME, StoreItems.COLUMN_NAME_PRICE}, new int[]
                        {R.id.detail_name, R.id.detail_price});
        itemAdapter.setViewBinder(new ViewBinder() {
            public boolean setViewValue(View view, Cursor cursor, int columnIndex)
            {
                TextView tv = (TextView) view;
                String str = cursor.getString(columnIndex);
                tv.setText(str);
                return true;
            }
        });
        list.setAdapter(itemAdapter);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart, menu);
        return true;
    }
    
}
