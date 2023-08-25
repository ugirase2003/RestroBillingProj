package com.example.billingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        Thread thread=new Thread(){

            public  void run(){
                try{
                    sleep(3000);

                }
                catch (Exception e){

                }
                finally {

                    SharedPreferences sp=getSharedPreferences("new",MODE_PRIVATE);

                    if(sp.getInt("start_main",0)==0){


                        startActivity(new Intent(Splash.this,Edit_table.class));


                    }

                    else
                    {

                        startActivity(new Intent(Splash.this,MainActivity.class));
                    }

                }
            }

        };thread.start();


    }
}