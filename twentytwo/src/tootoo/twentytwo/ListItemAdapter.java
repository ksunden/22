package tootoo.twentytwo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListItemAdapter extends ArrayAdapter<ListItem>{
    Context context;
    int layoutResourceId;
    ListItem data[] = null;
    
    public ListItemAdapter(Context context, int layoutResourceId, ListItem[] data)
    {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ListItemHolder holder = null;
        
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
        
        ListItem li = data[position];
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
