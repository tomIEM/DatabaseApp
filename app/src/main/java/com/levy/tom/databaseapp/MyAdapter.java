package com.levy.tom.databaseapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by util on 02/10/2015.
 */
public class MyAdapter extends ArrayAdapter<Message> {

    Context context;
    int layoutResourceId;
    ArrayList<Message> data = null;

    public MyAdapter(Context context, int layoutResourceId, ArrayList<Message> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MessageHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new MessageHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }
        else
        {
            holder = (MessageHolder)row.getTag();
        }

        Message message = data.get(position);
        holder.txtTitle.setText(message.getContent());
        holder.imgIcon.setImageResource(android.R.drawable.ic_menu_edit);

        return row;
    }

    static class MessageHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}
