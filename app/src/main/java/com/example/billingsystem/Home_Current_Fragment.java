package com.example.billingsystem;

import static com.example.billingsystem.Edit_table.table_no;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import de.hdodenhof.circleimageview.CircleImageView;



public class Home_Current_Fragment extends Fragment {
    BottomNavigationView bottomNavigationView;
    Button admin_btn;
    static  public int clickedImg=0;
    SliderView sliderView;// object of SliderView
    int[] images={R.drawable.bill1, R.drawable.bill2,R.drawable.bill3};


    TextView tableno;

    CircleImageView veg_img_btn,nonveg_img_btn,starter_btn,dessert_bnt,fastfood_btn,beverages_btn;
    public Home_Current_Fragment(){

    }

    public Home_Current_Fragment(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView=bottomNavigationView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        //we cant use methods of View class method directly in fragment because we are extending Fragment class not AppComaptActivity class
        //so that's why we have to inflate layout inside Views object
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        SharedPreferences sp=this.getActivity().getSharedPreferences("new", Context.MODE_PRIVATE);
        table_no=sp.getInt("start_main",0);

        tableno=view.findViewById(R.id.table_no_home);
        String s="Table no: "+table_no;
        tableno.setText(s);



        //imageview initialization as btn
        veg_img_btn=view.findViewById(R.id.veg_btn);
        nonveg_img_btn=view.findViewById(R.id.nonVegBtn);
        starter_btn=view.findViewById(R.id.starterBtn);
        dessert_bnt=view.findViewById(R.id.dessertBtn);
        fastfood_btn=view.findViewById(R.id.fastfoodBtn);
        beverages_btn=view.findViewById(R.id.beveragesBtn);









        admin_btn=view.findViewById(R.id.adminBtn);
        sliderView=view.findViewById(R.id.imageSlider);
        SliderAdapter sliderAdapter=new SliderAdapter(images,getActivity());
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

            admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(getActivity(), Admin_Auth.class));
            }
        });





            //now set Onclciklistener  on each img_btn_icon
        veg_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    clickedImg=R.id.veg_btn;
                    getActivity(). getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new MenuFragment()).commit();
                    bottomNavigationView.setSelectedItemId(R.id.menu_items);

                }
                catch (Exception e){
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

        nonveg_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    clickedImg=R.id.nonVegBtn;
                    getActivity(). getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new MenuFragment()).commit();
                    bottomNavigationView.setSelectedItemId(R.id.menu_items);

                }
                catch (Exception e){
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }
        });
        starter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    clickedImg=R.id.starterBtn;
                    getActivity(). getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new MenuFragment()).commit();
                    bottomNavigationView.setSelectedItemId(R.id.menu_items);

                }
                catch (Exception e){
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        dessert_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    clickedImg=R.id.dessertBtn;
                    getActivity(). getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new MenuFragment()).commit();
                    bottomNavigationView.setSelectedItemId(R.id.menu_items);

                }
                catch (Exception e){
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }
        });
        fastfood_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    clickedImg=R.id.fastfoodBtn;
                    getActivity(). getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new MenuFragment()).commit();
                    bottomNavigationView.setSelectedItemId(R.id.menu_items);

                }
                catch (Exception e){
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });
        beverages_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    clickedImg=R.id.beveragesBtn;
                    getActivity(). getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new MenuFragment()).commit();
                    bottomNavigationView.setSelectedItemId(R.id.menu_items);

                }
                catch (Exception e){
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }



            }
        });

        return view;


    }

}