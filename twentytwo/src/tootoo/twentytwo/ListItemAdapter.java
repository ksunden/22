package tootoo.twentytwo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * After doing some research on custom ListViews, we observed that using both
 * this class and a basic object class for a ListItem was the best way to
 * proceed. This class inflates the XML layout of a ListItem object and creates
 * an element in a ListView from it.
 * 
 * @author Natalie Davenport and Kyle Sunden
 * @version 11/17/2013
 */
public class ListItemAdapter extends ArrayAdapter<ListItem>{
    Context context;
    int layoutResourceId;
    ArrayList<ListItem> list = null;
    
    public ListItemAdapter(Context context, int layoutResourceId, ArrayList<ListItem> data)
    {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.list = data;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ListItemHolder holder = null;
        
        // If the row is null, inflate the TextViews and ImageView of the
        // ListItem object
        if(row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new ListItemHolder();
            holder.itemIcon = (ImageView) row.findViewById(R.id.orgImage);
            holder.itemTitle = (TextView) row.findViewById(R.id.orgTitle);
            holder.itemDesc = (TextView) row.findViewById(R.id.orgDescription);
            
            row.setTag(holder);
        }else
        {
            holder = (ListItemHolder) row.getTag();
        }
        
        // Assign the values of the ListItem object to the ListItem view
        ListItem li = list.get(position);
        holder.itemTitle.setText(li.title);
        holder.itemDesc.setText(li.desc);
        holder.itemIcon.setImageResource(li.image);
        
        return row;
    }
    
    static class ListItemHolder{
        ImageView itemIcon;
        TextView itemTitle;
        TextView itemDesc;
    }
}
