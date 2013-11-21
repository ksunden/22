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
    
    @Override
    public int getCount(){
        return mViews.size();
    }
    
    @Override
    public Object getItem(int position){
        return null;
    }
    
    @Override
    public long getItemId(int position){
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return mViews.get(position);
    }
    
    public int getItemIndex(View v){
        return mViews.indexOf(v);
    }
    
}
