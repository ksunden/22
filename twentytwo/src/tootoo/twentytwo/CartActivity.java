package tootoo.twentytwo;

import tootoo.twentytwo.StoreContract.StoreItems;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class CartActivity extends Activity{
    private static SQLiteDatabase db;
    private static MenuItem price;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        
        // Open database connection
        db = new StoreDbHelper(this).getWritableDatabase();
        
        // Retrieve items with positive quantity
        Cursor cursor = db.query(StoreItems.TABLE_NAME, null, StoreItems.COLUMN_NAME_QUANTITY + "> 0", null, null, null, null);
        ListView list = (ListView) findViewById(R.id.cart_listview);
        
        // TODO Use api test to use newer constructor for SimpleCursorAdapter
        // when available
        // Adapt the cursor to the listView
        SimpleCursorAdapter itemAdapter = new SimpleCursorAdapter(this, R.layout.item_preview, cursor, new String[] {StoreItems.COLUMN_NAME_NAME, StoreItems.COLUMN_NAME_PRICE, StoreItems.COLUMN_NAME_IMAGE_LOCATION, StoreItems.COLUMN_NAME_QUANTITY}, new int[] {R.id.preview_name, R.id.preview_price, R.id.preview_image, R.id.preview_quantity});
        itemAdapter.setViewBinder(new ViewBinder() {
            public boolean setViewValue(View view, Cursor cursor, int columnIndex){
                if(view instanceof EditText)
                {
                    // Set value of quantity to value in database
                    final EditText et = (EditText) view;
                    int qty = cursor.getInt(columnIndex);
                    et.setText(qty + "");
                    
                    // Following calls done in ViewBinder so that they bind
                    // listeners to specific instances.
                    // Done in EditText so that the code is only called once,
                    // and can change the EditText value
                    
                    // Table Id of the item that is being bound
                    final int tableId = cursor.getInt(cursor.getColumnIndex(StoreItems._ID));
                    
                    // Retrieves parent view as context for findViewById calls
                    View vp = (View) view.getParent();
                    
                    // Button to decrement the value, store to database, and
                    // update price
                    Button minus = (Button) vp.findViewById(R.id.preview_minus);
                    minus.setOnClickListener(new OnClickListener() {
                        
                        @Override
                        public void onClick(View v){
                            
                            int curr = Integer.parseInt("0" + et.getText().toString());
                            curr--;
                            if(curr < 0) curr = 0;
                            ContentValues values = new ContentValues();
                            values.put(StoreItems.COLUMN_NAME_QUANTITY, curr);
                            db.update(StoreItems.TABLE_NAME, values, StoreItems._ID + " = " + tableId, null);
                            updatePrice();
                            et.setText("" + curr);
                        }
                    });
                    
                    // Button to increment the value, store to database, and
                    // update price
                    Button plus = (Button) vp.findViewById(R.id.preview_plus);
                    plus.setOnClickListener(new OnClickListener() {
                        
                        @Override
                        public void onClick(View v){
                            int curr = Integer.parseInt("0" + et.getText().toString());
                            curr++;
                            ContentValues values = new ContentValues();
                            values.put(StoreItems.COLUMN_NAME_QUANTITY, curr);
                            db.update(StoreItems.TABLE_NAME, values, StoreItems._ID + " = " + tableId, null);
                            updatePrice();
                            et.setText("" + curr);
                        }
                    });
                }else if(view instanceof TextView)
                {
                    // Bind strings from database to TextViews
                    TextView tv = (TextView) view;
                    String str = cursor.getString(columnIndex);
                    str = str.replace("\n", " ");
                    if(columnIndex == cursor.getColumnIndex(StoreItems.COLUMN_NAME_PRICE)) str = "$" + str;
                    tv.setText(str);
                }else if(view instanceof ImageView)
                {
                    ImageView iv = (ImageView) view;
                    iv.setAdjustViewBounds(true);
                    iv.setImageResource(cursor.getInt(columnIndex));
                }
                return true;
            }
        });
        
        // Set the content of the ListView
        list.setAdapter(itemAdapter);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart, menu);
        
        // Set the price to initial conditions
        price = menu.getItem(0);
        updatePrice();
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.cart_buy_menu)
        {
            // Retrieve items with positive quantity from the database
            Cursor cursor = db.query(StoreItems.TABLE_NAME, null, StoreItems.COLUMN_NAME_QUANTITY + "> 0", null, null, null, null);
            cursor.moveToFirst();
            
            // Add Items with quantity to the email message
            String message = "";
            while(!cursor.isAfterLast())
            {
                message += cursor.getString(cursor.getColumnIndex(StoreItems.COLUMN_NAME_NAME)) + ":  " + cursor.getString(cursor.getColumnIndex(StoreItems.COLUMN_NAME_QUANTITY)) + "\n";
                cursor.moveToNext();
            }
            
            // Configure and start email intent
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {getResources().getString(R.string.store_email)});
            startActivity(Intent.createChooser(emailIntent, "Email: "));
        }
        return super.onOptionsItemSelected(item);
    }
    
    // Close database connection when activity ends;
    @Override
    protected void onDestroy(){
        db.close();
        super.onDestroy();
    }
    
    // Update the price displayed in the action bar to the current stored in the
    // database
    private static void updatePrice(){
        price.setTitle("$" + StoreDbHelper.calculateTotal(db));
    }
    
}
