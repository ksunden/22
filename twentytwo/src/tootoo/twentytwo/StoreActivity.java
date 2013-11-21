package tootoo.twentytwo;

import java.util.ArrayList;

import tootoo.twentytwo.StoreContract.StoreItems;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreActivity extends Activity{
    private Context mContext;
    private static SQLiteDatabase db;
    private static MenuItem price;
    private static ArrayList<Integer> tableIds;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        setTitle("Team Tootoo's Store");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        mContext = this;
        
        // Retrieve database of Team Tootoo merchandise
        db = new StoreDbHelper(this).getWritableDatabase();
        
        new StoreDbHelper(this).onUpgrade(db, 0, 0);
        
        // Retrieve items from the database and adapt with cursor adapter
        Cursor cursor = db.query(StoreItems.TABLE_NAME, new String[] {StoreItems._ID, StoreItems.COLUMN_NAME_NAME, StoreItems.COLUMN_NAME_IMAGE_LOCATION}, null, null, null, null, null);
        cursor.moveToFirst();
        
        ImageView blank = new ImageView(this);
        
        ArrayList<View> gridItems = new ArrayList<View>();
        tableIds = new ArrayList<Integer>();
        
        while(!cursor.isAfterLast())
        {
            int index = gridItems.indexOf(blank);
            
            tableIds.add(cursor.getInt(cursor.getColumnIndex(StoreItems._ID)));
            
            ImageView iv = new ImageView(this);
            iv.setAdjustViewBounds(true);
            iv.setPadding(10, 10, 10, 10);
            iv.setImageResource(cursor.getInt(cursor.getColumnIndex(StoreItems.COLUMN_NAME_IMAGE_LOCATION)));
            if(index == -1)
            {
                gridItems.add(iv);
                gridItems.add(blank);
                
            }else
            {
                gridItems.set(index, iv);
            }
            
            TextView tv = new TextView(this);
            tv.setTextSize(32);
            tv.setMaxLines(2);
            tv.setGravity(Gravity.CENTER);
            tv.setText(cursor.getString(cursor.getColumnIndex(StoreItems.COLUMN_NAME_NAME)));
            gridItems.add(tv);
            
            cursor.moveToNext();
        }
        
        Log.d("ArrayList", gridItems.toString());
        
        // Set up GridView with data and listener
        GridView grid = (GridView) findViewById(R.id.store_grid);
        grid.setGravity(Gravity.CENTER);
        grid.setAdapter(new StoreAdapter(gridItems));
        grid.setOnItemClickListener(new OnItemClickListener() {
            
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                
                AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
                LayoutInflater inflater = getLayoutInflater();
                
                // Determine which object in the database was selected.
                int viewIndex = ((StoreAdapter) arg0.getAdapter()).getItemIndex(arg1);
                int tableIdIndex = viewIndex / 2 - (viewIndex / 2) % 2 + viewIndex % 2;
                final int tableId = tableIds.get(tableIdIndex);
                
                // Retrieve the current item
                Cursor c = db.query(StoreItems.TABLE_NAME, null, "_id = " + tableId, null, null, null, null);
                c.moveToFirst();
                
                // Set the view for the alert
                View view = inflater.inflate(R.layout.item_detail, null);
                adb.setView(view);
                
                // Show the picture
                ImageView pic = (ImageView) view.findViewById(R.id.detail_image);
                pic.setImageResource(c.getInt(c.getColumnIndex(StoreItems.COLUMN_NAME_IMAGE_LOCATION)));
                
                // Display the name
                TextView name = (TextView) view.findViewById(R.id.detail_name);
                name.setText(c.getString(c.getColumnIndex(StoreItems.COLUMN_NAME_NAME)));
                
                // Display the price
                TextView price = (TextView) view.findViewById(R.id.detail_price);
                price.setText("$" + c.getInt(c.getColumnIndex(StoreItems.COLUMN_NAME_PRICE)));
                
                // Set the displayed quantity
                final EditText quantity = (EditText) view.findViewById(R.id.detail_quantity);
                quantity.setText("" + c.getInt(c.getColumnIndex(StoreItems.COLUMN_NAME_QUANTITY)));
                
                // Decrement the non-negative quantity
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
                
                // Increment the quantity
                Button plus = (Button) view.findViewById(R.id.detail_plus);
                plus.setOnClickListener(new OnClickListener() {
                    
                    @Override
                    public void onClick(View v){
                        int curr = Integer.parseInt("0" + quantity.getText().toString());
                        curr++;
                        if(curr < 0) curr = 0;
                        quantity.setText("" + curr);
                    }
                });
                
                // Update the database with the new value for the selected item
                adb.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                        ContentValues values = new ContentValues();
                        values.put(StoreItems.COLUMN_NAME_QUANTITY, Integer.parseInt(quantity.getText().toString()));
                        db.update(StoreItems.TABLE_NAME, values, "_id=" + tableId, null);
                        updatePrice();
                    }
                });
                
                // Update the database with the new value for the selected item
                adb.setOnCancelListener(new OnCancelListener() {
                    
                    @Override
                    public void onCancel(DialogInterface dialog){
                        dialog.dismiss();
                        ContentValues values = new ContentValues();
                        values.put(StoreItems.COLUMN_NAME_QUANTITY, Integer.parseInt(quantity.getText().toString()));
                        db.update(StoreItems.TABLE_NAME, values, "_id=" + tableId, null);
                        updatePrice();
                    }
                });
                
                // Show the dialog
                AlertDialog dialog = adb.create();
                dialog.show();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.store, menu);
        
        // Set the price to initial conditions
        price = menu.getItem(0);
        updatePrice();
        return true;
    }
    
    // Handle action to move to cart
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.store_cart_menu)
        {
            Intent intent = new Intent(mContext, CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onResume(){
        updatePrice();
        super.onResume();
    }
    
    // Close database connection when activity ends;
    @Override
    protected void onDestroy(){
        db.close();
        System.gc();
        super.onDestroy();
    }
    
    // Update the price displayed in the action bar to the current stored in the
    // database
    private static void updatePrice(){
        if(price != null) price.setTitle("$" + StoreDbHelper.calculateTotal(db));
    }
}
