package tootoo.twentytwo;

import tootoo.twentytwo.StoreContract.StoreItems;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class StoreActivity extends Activity {
    Context mContext;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        SQLiteDatabase db = new StoreDbHelper(this).getWritableDatabase();
        setContentView(R.layout.activity_store);
        Cursor cursor = db.query(StoreItems.TABLE_NAME, new String[]{StoreItems._ID,StoreItems.COLUMN_NAME_NAME}, null, null, null, null, null);
        GridView grid =(GridView) findViewById(R.id.store_grid);
        SimpleCursorAdapter itemAdapter = new SimpleCursorAdapter(this, R.layout.item_preview, cursor, new String[]
                {StoreItems.COLUMN_NAME_NAME}, new int[]
                        {R.id.preview_item_name});
        itemAdapter.setViewBinder(new ViewBinder() {
            public boolean setViewValue(View view, Cursor cursor, int columnIndex)
            {
                TextView tv = (TextView) view;
                String str = cursor.getString(columnIndex);
                tv.setText(str);
                return true;
            }
        });
        grid.setAdapter(itemAdapter);
        grid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
                adb.setMessage(arg1.toString());
                adb.create().show();
            }});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.store, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // TODO Auto-generated method stub
        if(item.getTitle().equals("Cart"))
        {
            Intent intent = new Intent(mContext, CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    
}
