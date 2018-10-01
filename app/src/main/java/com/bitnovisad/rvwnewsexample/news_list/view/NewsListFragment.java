package com.bitnovisad.rvwnewsexample.news_list.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bitnovisad.rvwnewsexample.R;
import com.bitnovisad.rvwnewsexample.news_list.adapter.NewsListAdapter;
import com.bitnovisad.rvwnewsexample.news_list.model.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This is RecyclerView Fragment!!!
 */

public class NewsListFragment extends Fragment {

    private static final String URL_DATA = "https://newsapi.org/v2/top-headlines?sources=bbc-sport&apiKey=89cecfc4fed94fbe9ad1f01407726463";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<NewsItem> newsItems;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ////inflate layout for fragment
        View v = inflater.inflate(R.layout.news_fragment, container, false);
        //super.onCreateView(inflater, container, savedInstanceState)

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewFragmentLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        newsItems = new ArrayList<>();

        //------------------------------------------------------------------------------------------

//        for(int i=0; i<=10; i++){
//            NewsItem newsItem = new NewsItem("Vazan naslov ", "Neki opis novinskog clanka.");
//            newsItems.add(newsItem);
//
//        }
//
//        adapter = new NewsListAdapter(newsItems, getActivity());
//        recyclerView.setAdapter(adapter);

        //------------------------------------------------------------------------------------------
        loadRecyclerViewData();
        return v;
    }

    //method for loading recyclerview data form server
    public void loadRecyclerViewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("articles");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                NewsItem news = new NewsItem(
                                        obj.getString("title"),
                                        // TODO resolve problem with date formating
                                        // dateFormater(obj.getString("publishedAt")),
                                        obj.getString("publishedAt"),
                                        obj.getString("urlToImage")
                                );
                                newsItems.add(news);
                            }
                            adapter = new NewsListAdapter(newsItems, getActivity().getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueve = Volley.newRequestQueue(getActivity());
        requestQueve.add(stringRequest);

    }

    //method for formating date
//    public String dateFormater(String object) {
//        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
//
//        Date d = null;
//        try {
//            d = input.parse(object);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            Toast.makeText(getActivity(), "Bad date format", Toast.LENGTH_LONG).show();
//        }
//        String formatted = output.format(d);
//
//        return formatted;
//    }
}
