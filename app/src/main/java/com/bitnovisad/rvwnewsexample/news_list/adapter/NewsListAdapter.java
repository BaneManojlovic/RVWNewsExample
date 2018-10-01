package com.bitnovisad.rvwnewsexample.news_list.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bitnovisad.rvwnewsexample.R;
import com.bitnovisad.rvwnewsexample.details.view.NewsDetailsFragment;
import com.bitnovisad.rvwnewsexample.home.view.HomeFragment;
import com.bitnovisad.rvwnewsexample.news_list.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder>{

    public static FragmentManager fragmnetManager;
    private List<NewsItem> newsItems;
    private Context context;

    public NewsListAdapter(List<NewsItem> newsItems, Context context) {
        this.newsItems = newsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final NewsItem newsItem = newsItems.get(position);
        holder.textViewTitle.setText(newsItem.getTitle());
        holder.textViewDescription.setText(newsItem.getDescription());

        //call picasso to get image url for picasso support library: 2.5.2
        Picasso.with(context)
                .load(newsItem.getUrlToImage())
                .resize(500, 500)
                .centerCrop()
                .into(holder.articleImageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, newsItem.getTitle(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, NewsDetailsFragment.class);
                intent.putExtra("urlToImage", newsItem.getUrlToImage());
                intent.putExtra("title", newsItem.getTitle());
                intent.putExtra("description", newsItem.getDescription());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewTitle;
        public TextView textViewDescription;
        public ImageView articleImageView;
        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitle = (TextView) itemView.findViewById(R.id.articleTitle);
            textViewDescription = (TextView) itemView.findViewById(R.id.articleDescription);
            articleImageView = (ImageView) itemView.findViewById(R.id.articleImage);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutItem);
        }
    }
}
