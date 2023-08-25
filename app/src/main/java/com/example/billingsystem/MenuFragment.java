package com.example.billingsystem;

import static com.example.billingsystem.Home_Current_Fragment.clickedImg;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapter.VPAdapter;
import tabItem.Beverages;
import tabItem.Dessert;
import tabItem.Nonveg;
import tabItem.Option;
import tabItem.Starter;

//here we are implementing Tab..Med.. we can also direct pass anonymous class object like setOnclickL...
public class MenuFragment extends Fragment implements TabLayoutMediator.TabConfigurationStrategy {



ViewPager2 viewPager2;

TabLayout tabLayout;
ArrayList<String>tabTitle=new ArrayList<>();//for storing tab title



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_menu, container, false);
        viewPager2 = view.findViewById(R.id.viewPager2);
        tabLayout=view.findViewById(R.id.tabLayout);

        tabTitle.add("Veg");
        tabTitle.add("Non-veg");
        tabTitle.add("Dessert");
        tabTitle.add("Beverages");
        tabTitle.add("Starter");
        tabTitle.add("FastFood");

        setViewPagerAdapter();
        // this our viewpager is linking to tab layout
        new TabLayoutMediator(tabLayout, viewPager2, this).attach();//attach method is imp due to


       if(clickedImg!=0);{

           switch (clickedImg){
               case R.id.veg_btn:
                   selectPage(0);

                   break;
               case R.id.nonVegBtn:
                   selectPage(1);

                   break;
               case R.id.dessertBtn:
                   selectPage(2);

                   break;
               case R.id.starterBtn:
                   selectPage(4);

                   break;
               case R.id.beveragesBtn:
                   selectPage(3);

                   break;
               case R.id.fastfoodBtn:
                   selectPage(5);

                   break;
           }





        }










        return view;
    }

    private void setViewPagerAdapter() {

        VPAdapter viewPager2Adapter = new VPAdapter(getActivity());
        ArrayList<Fragment> fragmentList = new ArrayList<>(); //creates an ArrayList of Fragments

        //now add all fragments which u want to show in  tab layout as tab item
        fragmentList.add(new VegFragment());
        fragmentList.add(new Nonveg());
        fragmentList.add(new Dessert());
        fragmentList.add(new Beverages());
        fragmentList.add(new Starter());
        fragmentList.add(new Option());


        viewPager2Adapter.setData(fragmentList); //sets the data for the viewpager's  adapter similar like passing list or data while using recyclerview
        viewPager2.setAdapter(viewPager2Adapter);






    }
    void selectPage(int pageIndex){
        tabLayout.setScrollPosition(pageIndex,0f,true);
        viewPager2.setCurrentItem(pageIndex);
    }




    //as we have implemented Tab..Med. so we have to override this method
    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        tab.setText(tabTitle.get(position));
    }
}