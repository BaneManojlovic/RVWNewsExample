package com.bitnovisad.rvwnewsexample.details.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitnovisad.rvwnewsexample.R;
import com.squareup.picasso.Picasso;

public class NewsDetailsFragment extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details_fragment);

        getIncomingIntent();
    }


    //method for catching incoming intent
    public void getIncomingIntent(){
        if(getIntent().hasExtra("urlToImage") && getIntent().hasExtra("title")
                && getIntent().hasExtra("description")){

            String imageUrl = getIntent().getStringExtra("urlToImage");
            String newsTitle = getIntent().getStringExtra("title");
            String newsDescription = getIntent().getStringExtra("description");

            setImage(imageUrl, newsTitle, newsDescription);

        }
    }

    //seting values from intent into layout
    public void setImage(String imageUrl, String newsTitle, String newsDescription){

        TextView newsT = (TextView) findViewById(R.id.newsDetailsNewsTitle);
        newsT.setText(newsTitle);

        TextView newsDesc = (TextView) findViewById(R.id.newsDetailsDescription);
        newsDesc.setText(newsDescription);

        ImageView image = (ImageView) findViewById(R.id.newsDetailsImageView);
        Picasso.with(this)
                .load(imageUrl)
                .resize(400, 400)
                .centerCrop()
                .into(image);
    }



}
