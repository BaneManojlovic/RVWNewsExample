
package com.bitnovisad.rvwnewsexample.main.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bitnovisad.rvwnewsexample.R;
import com.bitnovisad.rvwnewsexample.home.view.HomeFragment;

public class MainActivity extends AppCompatActivity {

    //instanitiate Fragment Manager for handling all fragments via MainActivity
    public static FragmentManager fragmnetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defining function for Fragment Manager to open fragment container layout
        fragmnetManager = getSupportFragmentManager();
        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }
            FragmentTransaction fragmentTransaction = fragmnetManager.beginTransaction();
            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.add(R.id.fragment_container, homeFragment, null).commit();

        }
    }
}
