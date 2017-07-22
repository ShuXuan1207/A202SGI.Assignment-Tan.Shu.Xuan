package com.inti.student.androidassignment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

public class NewsListAdapter extends ArrayAdapter<Article> {

    private List<Article> newsList;
    Context context;

    public NewsListAdapter(Context context, List<Article> newsList) {
        super(context, R.layout.item, newsList);
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item, parent, false);
        }

        Article currentItem = newsList.get(position);

        TextView author = (TextView) convertView.findViewById(R.id.author);
        ImageView imageUrl = (ImageView) convertView.findViewById(R.id.imageUrl);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView dateTime = (TextView) convertView.findViewById(R.id.dateTime);
        TextView url = (TextView) convertView.findViewById(R.id.url);



        author.setText(currentItem.getAuthor());
        new DownloadImageTask(imageUrl).execute(currentItem.getUrlToImage());
        title.setText(currentItem.getTitle());
        description.setText(currentItem.getDescription());
        dateTime.setText(currentItem.getPublishedAt());
        url.setText(currentItem.getUrl());

        return convertView;

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            }

            catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
