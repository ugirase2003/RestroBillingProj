package com.example.billingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Edit_table extends AppCompatActivity {
    public  static  int table_no;//table no will be change only by admin
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_table);
        getSupportActionBar().hide();
        EditText table=findViewById(R.id.table);
        Button btn=findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(table.getText().toString().isEmpty())
                    table.setError("Enter Table no");
                else{


                    table_no=Integer.parseInt(table.getText().toString());
                    SharedPreferences sp=getSharedPreferences("new",MODE_PRIVATE);
                    SharedPreferences.Editor set=sp.edit();
                    set.putInt("start_main",table_no).apply();

                    startActivity(new Intent(Edit_table.this,MainActivity.class));





                }
            }
        });


    }
}