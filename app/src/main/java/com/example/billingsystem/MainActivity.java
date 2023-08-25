package com.example.billingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity<table> extends AppCompatActivity {
FirebaseFirestore firebaseFirestore;


public static ArrayList<Map>Added_items=new ArrayList<>();//it is use storing the selected item
public static ArrayList<new_menu_model>fetched_data=new ArrayList<>();//it is  fetch menu items data from fire store
public static boolean isDataFetched=false;


public static int Total_Global=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //hide action bar of activity
        getSupportActionBar().hide();
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        //setting default fragment as Home_Current_Fragment
        //home_frame is container frame
        getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new Home_Current_Fragment(bottomNavigationView)).commit();

        //Bottom Navigation View Initialization






//set actions for selected item

            bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selected = null;
                    switch (item.getItemId()) {
                        case R.id.menu_items:
                            selected = new MenuFragment();
                            break;
                        case R.id.offers:
                            selected = new OfferFragment();
                            break;
                        case R.id.cart:
                            selected = new CartFragment();
                            break;
                        case R.id.home:
                            selected = new Home_Current_Fragment(bottomNavigationView);
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.home_frame, selected).commit();

                    return true;

                }
            });

//menu items fetching from database
            //  firebaseFirestore=FirebaseFirestore.getInstance();
        
    }

   }