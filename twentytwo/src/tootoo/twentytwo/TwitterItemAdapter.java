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

public class TwitterItemAdapter extends ArrayAdapter<TwitterItem>{
    Context context;
    int layoutResourceId;
    ArrayList<TwitterItem> tweetList = null;
    
    public TwitterItemAdapter(Context context, int layoutResourceId, ArrayList<TwitterItem> data)
    {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.tweetList = data;
    }
    
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        TwitterItemHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new TwitterItemHolder();
            holder.itemIcon = (ImageView) row.findViewById(R.id.tweetImage);
            holder.itemUserName = (TextView) row.findViewById(R.id.tweetUserName);
            holder.itemContent = (TextView) row.findViewById(R.id.tweetContent);
            
            row.setTag(holder);
        }else
        {
            holder = (TwitterItemHolder) row.getTag();
        }
        
        TwitterItem ti = tweetList.get(position);
        holder.itemIcon.setImageResource(ti.tweetImage);
        holder.itemUserName.setText(ti.tweetUserName);
        holder.itemContent.setText(ti.tweetContent);
        
        return row;
    }
    
    public void add(TwitterItem item){
        tweetList.add(item);
    }
    
    static class TwitterItemHolder{
        ImageView itemIcon;
        TextView itemUserName;
        TextView itemContent;
    }
}
