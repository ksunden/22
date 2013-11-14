package tootoo.twentytwo;

import tootoo.twentytwo.StoreContract.StoreItems;
import android.app.Activity;
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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class CartActivity extends Activity{
    private static SQLiteDatabase db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        
        db = new StoreDbHelper(this).getWritableDatabase();
        Cursor cursor = db.query(StoreItems.TABLE_NAME, null, StoreItems.COLUMN_NAME_QUANTITY + "> 0", null, null, null, null);
        ListView list = (ListView) findViewById(R.id.cart_listview);
        SimpleCursorAdapter itemAdapter = new SimpleCursorAdapter(this, R.layout.item_detail, cursor, new String[] {StoreItems.COLUMN_NAME_NAME, StoreItems.COLUMN_NAME_PRICE, StoreItems.COLUMN_NAME_IMAGE_LOCATION, StoreItems.COLUMN_NAME_QUANTITY}, new int[] {R.id.detail_name, R.id.detail_price, R.id.detail_image, R.id.detail_quantity});
        itemAdapter.setViewBinder(new ViewBinder() {
            public boolean setViewValue(View view, Cursor cursor, int columnIndex){
                if(view instanceof EditText)
                {
                    final EditText et = (EditText) view;
                    int qty = cursor.getInt(columnIndex);
                    et.setText(qty + "");
                    
                    View vp = (View) view.getParent();
                    Button minus = (Button) vp.findViewById(R.id.detail_minus);
                    minus.setOnClickListener(new OnClickListener() {
                        
                        @Override
                        public void onClick(View v){
                            
                            int curr = Integer.parseInt("0" + et.getText().toString());
                            curr--;
                            if(curr < 0) curr = 0;
                            
                            et.setText("" + curr);
                        }
                    });
                    
                    Button plus = (Button) vp.findViewById(R.id.detail_plus);
                    plus.setOnClickListener(new OnClickListener() {
                        
                        @Override
                        public void onClick(View v){
                            int curr = Integer.parseInt("0" + et.getText().toString());
                            curr++;
                            et.setText("" + curr);
                        }
                    });
                }else if(view instanceof TextView)
                {
                    TextView tv = (TextView) view;
                    String str = cursor.getString(columnIndex);
                    if(columnIndex == cursor.getColumnIndex(StoreItems.COLUMN_NAME_PRICE)) str = "$" + str;
                    tv.setText(str);
                }
                return true;
            }
        });
        list.setAdapter(itemAdapter);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart, menu);
        MenuItem item = menu.getItem(0);
        item.setTitle("$" + StoreDbHelper.calculateTotal(db));
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.cart_buy_menu)
        {
            String message = "";
            SQLiteDatabase db = new StoreDbHelper(this).getWritableDatabase();
            Cursor cursor = db.query(StoreItems.TABLE_NAME, null, StoreItems.COLUMN_NAME_QUANTITY + "> 0", null, null, null, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                message += cursor.getString(cursor.getColumnIndex(StoreItems.COLUMN_NAME_NAME)) + ":  " + cursor.getString(cursor.getColumnIndex(StoreItems.COLUMN_NAME_QUANTITY)) + "\n";
                cursor.moveToNext();
            }
            
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_TEXT, message);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"k10ks03@kzoo.edu"});
            startActivity(Intent.createChooser(emailIntent, "Email: "));
        }
        return super.onOptionsItemSelected(item);
    }
    
}
