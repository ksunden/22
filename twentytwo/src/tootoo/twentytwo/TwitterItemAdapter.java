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
 * Similar to the ListItemAdapter class, this class takes a TwitterItem object,
 * retrieves its data, and adds the resulting view to SocialActivity's ListView
 * of tweets.
 * 
 * @author Natalie Davenport and Kyle Sunden
 * @version 11/17/2013
 */
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
        
        // If the row is null, inflate the ImageView and TextViews of the
        // TwitterItem XML layout
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
        
        // Set the contents of the view to the values of the TwitterItem object
        TwitterItem ti = tweetList.get(position);
        holder.itemUserName.setText(ti.tweetUserName);
        holder.itemContent.setText(ti.tweetContent);
        holder.itemIcon.setImageBitmap(ti.tweetImage);
        
        return row;
    }
    
    static class TwitterItemHolder{
        ImageView itemIcon;
        TextView itemUserName;
        TextView itemContent;
    }
}
