package com.inti.student.androidassignment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;

/*
    This Java File contains both Adapter and ViewHolder for Recycler View
*/

class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    public ImageView imageUrl;
    public TextView title, description, dateTime, url;
    private ItemClickListener mItemClickListener;

    public NewsViewHolder(View itemView) {
        super(itemView);

        imageUrl = (ImageView) itemView.findViewById(R.id.imageUrl);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        dateTime = (TextView) itemView.findViewById(R.id.dateTime);
        url = (TextView) itemView.findViewById(R.id.url);

        //Set Event
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }

    public void setItemClickListener (ItemClickListener mItemClickListener) {

        this.mItemClickListener = mItemClickListener;

    }

    @Override
    public void onClick(View v) {

        mItemClickListener.onClick(v,getAdapterPosition(), false);

    }

    @Override
    public boolean onLongClick(View v) {
        mItemClickListener.onClick(v,getAdapterPosition(), true);
        return true;
    }
}

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private NewsObject newsObject;
    private Context context;
    private LayoutInflater inflater;
    HistoryDatabaseHelper mHistoryDatabaseHelper;

    public NewsRecyclerAdapter(NewsObject newsObject, Context context) {
        this.newsObject = newsObject;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {


        new DownloadImageTask(holder.imageUrl).execute(newsObject.getArticles().get(position).getUrlToImage());
        holder.title.setText(newsObject.getArticles().get(position).getTitle());
        holder.description.setText(newsObject.getArticles().get(position).getDescription());
        holder.dateTime.setText(newsObject.getArticles().get(position).getPublishedAt());
        holder.url.setText(newsObject.getArticles().get(position).getUrl());



        final String author = newsObject.getArticles().get(position).getAuthor();
        final String imageUrl = newsObject.getArticles().get(position).getUrlToImage();
        final String title = newsObject.getArticles().get(position).getTitle();
        final String description = newsObject.getArticles().get(position).getDescription();
        final String dateTime = newsObject.getArticles().get(position).getPublishedAt();
        final String url = newsObject.getArticles().get(position).getUrl();


        holder.setItemClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick) {
                    mHistoryDatabaseHelper = new HistoryDatabaseHelper(context);
                    mHistoryDatabaseHelper.addData(author, imageUrl, title, description, dateTime, url);

                    String s = newsObject.getArticles().get(position).getUrl();
                    Intent intent = new Intent(context, InAppBrowserActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("url", s);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsObject.articles.size();
    }
}

//Method to store the actual image into a bitmap to be set into an Image View
class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
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

