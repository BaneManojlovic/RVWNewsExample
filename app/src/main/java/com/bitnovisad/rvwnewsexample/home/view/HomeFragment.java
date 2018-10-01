package com.bitnovisad.rvwnewsexample.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bitnovisad.rvwnewsexample.R;
import com.bitnovisad.rvwnewsexample.main.view.MainActivity;
import com.bitnovisad.rvwnewsexample.news_list.view.NewsListFragment;

public class HomeFragment extends Fragment {

    //opens NewsListFragment which containes RecyclerView
    private Button btnOpen;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflate layout HomeFragement
        View v = inflater.inflate(R.layout.home_fragment, container, false);

        //defining function for button "OPEN FRAGMENT RECYCLERVIEW"
        btnOpen = v.findViewById(R.id.buttonOpenNews);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click();
            }
        });

        return v;
    }

    //method for handling click event on button "OPEN FRAGMENT RECYCLERVIEW"
    public  void click(){
        Toast.makeText(getActivity(), "Opening RecyclerView List", Toast.LENGTH_LONG).show();
        MainActivity.fragmnetManager.beginTransaction().replace(R.id.fragment_container, new NewsListFragment()).addToBackStack(null).commit();  //.addToBackStack(null) provide step back in app
    }
}
