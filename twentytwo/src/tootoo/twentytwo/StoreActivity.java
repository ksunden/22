package tootoo.twentytwo;

import tootoo.twentytwo.StoreContract.StoreItems;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class StoreActivity extends Activity{
    private Context mContext;
    private static SQLiteDatabase db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mContext = this;
        db = new StoreDbHelper(this).getWritableDatabase();
        Log.d("Store", "" + StoreDbHelper.calculateTotal(db));
        setContentView(R.layout.activity_store);
        Cursor cursor = db.query(StoreItems.TABLE_NAME, new String[] {StoreItems._ID, StoreItems.COLUMN_NAME_NAME}, null, null, null, null, null);
        GridView grid = (GridView) findViewById(R.id.store_grid);
        SimpleCursorAdapter itemAdapter = new SimpleCursorAdapter(this, R.layout.item_preview, cursor, new String[] {StoreItems.COLUMN_NAME_NAME, StoreItems._ID}, new int[] {R.id.preview_item_name, R.id.preview_tableId});
        itemAdapter.setViewBinder(new ViewBinder() {
            public boolean setViewValue(View view, Cursor cursor, int columnIndex){
                if(view instanceof TextView)
                {
                    TextView tv = (TextView) view;
                    String str = cursor.getString(columnIndex);
                    tv.setText(str);
                }else if(view instanceof SeekBar)
                {
                    ((SeekBar) view).setProgress(cursor.getInt(columnIndex));
                }
                return true;
            }
        });
        grid.setAdapter(itemAdapter);
        grid.setOnItemClickListener(new OnItemClickListener() {
            
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
                LayoutInflater inflater = getLayoutInflater();
                final SeekBar id = (SeekBar) arg1.findViewById(R.id.preview_tableId);
                
                Cursor c = db.query(StoreItems.TABLE_NAME, null, "_id = " + id.getProgress(), null, null, null, null);
                c.moveToFirst();
                
                View view = inflater.inflate(R.layout.item_detail, null);
                adb.setView(view);
                
                TextView name = (TextView) view.findViewById(R.id.detail_name);
                name.setText(c.getString(c.getColumnIndex(StoreItems.COLUMN_NAME_NAME)));
                
                TextView price = (TextView) view.findViewById(R.id.detail_price);
                price.setText("$" + c.getInt(c.getColumnIndex(StoreItems.COLUMN_NAME_PRICE)));
                
                final EditText quantity = (EditText) view.findViewById(R.id.detail_quantity);
                quantity.setText("" + c.getInt(c.getColumnIndex(StoreItems.COLUMN_NAME_QUANTITY)));
                
                Button minus = (Button) view.findViewById(R.id.detail_minus);
                minus.setOnClickListener(new OnClickListener() {
                    
                    @Override
                    public void onClick(View v){
                        int curr = Integer.parseInt("0" + quantity.getText().toString());
                        curr--;
                        if(curr < 0) curr = 0;
                        quantity.setText("" + curr);
                    }
                });
                
                Button plus = (Button) view.findViewById(R.id.detail_plus);
                plus.setOnClickListener(new OnClickListener() {
                    
                    @Override
                    public void onClick(View v){
                        int curr = Integer.parseInt("0" + quantity.getText().toString());
                        curr++;
                        quantity.setText("" + curr);
                    }
                });
                
                adb.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                        ContentValues values = new ContentValues();
                        values.put(StoreItems.COLUMN_NAME_QUANTITY, Integer.parseInt(quantity.getText().toString()));
                        db.update(StoreItems.TABLE_NAME, values, "_id=" + id.getProgress(), null);
                    }
                });
                adb.create().show();
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.store, menu);
        MenuItem item = menu.getItem(0);
        item.setEnabled(true);
        item.setTitle("$" + StoreDbHelper.calculateTotal(db));
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.store_cart_menu)
        {
            Intent intent = new Intent(mContext, CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
