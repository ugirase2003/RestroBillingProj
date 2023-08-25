package com.example.billingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_Auth extends AppCompatActivity {
    static  String Username="admin@2003",Password="admin123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_auth);
        EditText pass,username;
        Button login;

        pass=findViewById(R.id.password);
        username=findViewById(R.id.username);
        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass.getText().toString().trim().length()==0)
                    pass.setError("Enter Username");
               else if(username.getText().toString().trim().length()==0)
                    username.setError("Enter Password");
               else if(pass.getText().toString().trim().equals(Password) && username.getText().toString().trim().equals(Username))
                   startActivity(new Intent(Admin_Auth.this,Admin_Activity.class));
               else
                    Toast.makeText(Admin_Auth.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
            }
        });




    }
}