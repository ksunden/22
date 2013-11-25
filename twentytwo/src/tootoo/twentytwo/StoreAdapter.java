package tootoo.twentytwo;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class StoreAdapter extends BaseAdapter{
    private ArrayList<View> mViews;
    
    public StoreAdapter(ArrayList<View> list)
    {
        mViews = list;
    }
    
    // Returns number of items in the ArrayList
    @Override
    public int getCount(){
        return mViews.size();
    }
    
    // Not Used
    @Override
    public Object getItem(int position){
        return null;
    }
    
    // Not used
    @Override
    public long getItemId(int position){
        return 0;
    }
    
    // Returns the view at a given position
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return mViews.get(position);
    }
    
    // Returns the index of the view in the ArrayList, or -1 if it is not there
    public int getItemIndex(View v){
        return mViews.indexOf(v);
    }
    
}
