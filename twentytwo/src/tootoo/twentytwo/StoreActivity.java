package tootoo.twentytwo;

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
import android.widget.SeekBar;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class StoreActivity extends Activity{
    private Context mContext;
    private static SQLiteDatabase db;
    private static MenuItem price;
    
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
        // TODO Use api test to use newer constructor for SimpleCursorAdapter
        // when available
        SimpleCursorAdapter itemAdapter = new SimpleCursorAdapter(this, R.layout.item_preview, cursor, new String[] {StoreItems._ID, StoreItems.COLUMN_NAME_NAME, StoreItems.COLUMN_NAME_IMAGE_LOCATION}, new int[] {R.id.preview_tableId, R.id.preview_item_name, R.id.preview_image});
        itemAdapter.setViewBinder(new ViewBinder() {
            public boolean setViewValue(View view, Cursor cursor, int columnIndex){
                Log.d("ViewBinding", "" + columnIndex + " " + cursor.getColumnName(columnIndex) + "  " + view.toString());
                if(view instanceof ImageView)
                {
                    ImageView iv = (ImageView) view;
                    iv.setImageResource(cursor.getInt(columnIndex));
                }else if(view instanceof TextView)
                {
                    // Bind name to the view
                    TextView tv = (TextView) view;
                    String str = cursor.getString(columnIndex);
                    tv.setText(str);
                }else if(view instanceof SeekBar)
                {
                    // Bind the table id to the hidden SeekBar
                    ((SeekBar) view).setProgress(cursor.getInt(columnIndex));
                }
                return true;
            }
        });
        
        // Set up GridView with data and listener
        GridView grid = (GridView) findViewById(R.id.store_grid);
        grid.setAdapter(itemAdapter);
        grid.setOnItemClickListener(new OnItemClickListener() {
            
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
                
                AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
                LayoutInflater inflater = getLayoutInflater();
                
                // Determine which object in the database was selected. Uses
                // hidden SeekBar to pass integer value.
                final SeekBar id = (SeekBar) arg1.findViewById(R.id.preview_tableId);
                
                // Retrieve the current item
                Cursor c = db.query(StoreItems.TABLE_NAME, null, "_id = " + id.getProgress(), null, null, null, null);
                c.moveToFirst();
                
                // Set the view for the alert
                View view = inflater.inflate(R.layout.item_detail, null);
                adb.setView(view);
                
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
                        db.update(StoreItems.TABLE_NAME, values, "_id=" + id.getProgress(), null);
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
                        db.update(StoreItems.TABLE_NAME, values, "_id=" + id.getProgress(), null);
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
        price.setTitle("$" + StoreDbHelper.calculateTotal(db));
    }
}
