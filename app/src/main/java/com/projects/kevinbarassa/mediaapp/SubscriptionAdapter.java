package com.projects.kevinbarassa.mediaapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kevin Barassa on 30-Jan-17.
 */

public class SubscriptionAdapter extends BaseAdapter {


    Context context;

    ArrayList<SubscriptionItem> bean;
    Typeface fonts1,fonts2;





    public SubscriptionAdapter(Context context, ArrayList<SubscriptionItem> bean) {


        this.context = context;
        this.bean = bean;
    }


    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public Object getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        fonts1 =  Typeface.createFromAsset(context.getAssets(),
                "fonts/Lato-Light.ttf");

        fonts2 = Typeface.createFromAsset(context.getAssets(),
                "fonts/Lato-Regular.ttf");

        ViewHolder viewHolder = null;

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.subscription_item,null);

            viewHolder = new ViewHolder();

            viewHolder.newsimage1 = (ImageView)convertView.findViewById(R.id.newsimage1);
            viewHolder.newsimage2 = (ImageView)convertView.findViewById(R.id.newsimage2);
            viewHolder.more = (ImageView)convertView.findViewById(R.id.more);
            viewHolder.newsname = (TextView)convertView.findViewById(R.id.newsname);
            viewHolder.time = (TextView)convertView.findViewById(R.id.time);
            viewHolder.news = (TextView)convertView.findViewById(R.id.news);
            viewHolder.newssub = (TextView)convertView.findViewById(R.id.newssub);
            viewHolder.intrest = (TextView)convertView.findViewById(R.id.intrest);




            viewHolder.newsname.setTypeface(fonts1);
            viewHolder.time.setTypeface(fonts1);
            viewHolder.news.setTypeface(fonts2);
            viewHolder.newssub.setTypeface(fonts1);
            viewHolder.intrest.setTypeface(fonts1);

            convertView.setTag(viewHolder);


        }else {

            viewHolder = (ViewHolder)convertView.getTag();
        }







        SubscriptionItem bean = (SubscriptionItem) getItem(position);

        viewHolder.newsimage1.setImageResource(bean.getNewsimage1());
        viewHolder.newsimage2.setImageResource(bean.getNewsimage2());
        viewHolder.more.setImageResource(bean.getMore());
        viewHolder.newsname.setText(bean.getNewsname());
        viewHolder.time.setText(bean.getTime());
        viewHolder.news.setText(bean.getNews());
        viewHolder.newssub.setText(bean.getNewssub());
        viewHolder.intrest.setText(bean.getIntrest());








        return convertView;
    }

    private class ViewHolder{

        ImageView newsimage1;
        ImageView newsimage2;
        ImageView  more;
        TextView newsname;
        TextView time;
        TextView news;
        TextView newssub;
        TextView intrest;






    }
}