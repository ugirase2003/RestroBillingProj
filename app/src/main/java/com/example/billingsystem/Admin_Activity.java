package com.example.billingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class Admin_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().hide();
        Toolbar toolbar=findViewById(R.id.admin_toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_for_admin_frag,new Add_Items()).commit();





        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Fragment selected=null;

                switch (menuItem.getItemId()){
                    case R.id.orders:
                        selected=new Current_Order();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_for_admin_frag,selected).commit();
                        break;
                    case R.id.add_item:
                        selected=new Add_Items();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_for_admin_frag,selected).commit();
                         break;
                    case R.id.see_history:
                        selected=new Order_Record();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_for_admin_frag,selected).commit();
                        break;
                    case R.id.offers:
                        selected=new add_offer();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_for_admin_frag,selected).commit();
                        break;
                    case R.id.mng_offers:
                        selected=new Offer_Admin();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_for_admin_frag,selected).commit();
                        break;

                    case R.id.mng_menu:
                        selected=new Admin_Menu();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_for_admin_frag,selected).commit();
                        break;
                    case R.id.mng_admin:
                        selected=new Manage_Admin();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_for_admin_frag,selected).commit();
                        break;

                }

                return false;

            }
        });



    }
}