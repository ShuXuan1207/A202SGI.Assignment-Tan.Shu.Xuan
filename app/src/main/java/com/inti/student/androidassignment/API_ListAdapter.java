package com.inti.student.androidassignment;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class API_ListAdapter extends ArrayAdapter<API> {

    private List<API> apiList;
    Context context;

    public API_ListAdapter(Context context, List<API> newsList) {
        super(context, R.layout.api_item, newsList);
        this.context = context;
        this.apiList = newsList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.api_item, parent, false);
        }

        API currentItem = apiList.get(position);

        TextView websiteName = (TextView) convertView.findViewById(R.id.websiteName);
        TextView apiKey = (TextView) convertView.findViewById(R.id.apiKey);

        websiteName.setText(currentItem.getWebsiteName());
        apiKey.setText(currentItem.getUrl());

        return convertView;

    }
}
