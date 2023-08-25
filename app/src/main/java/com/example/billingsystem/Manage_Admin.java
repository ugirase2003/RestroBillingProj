package com.example.billingsystem;

import static com.example.billingsystem.Admin_Auth.Password;
import static com.example.billingsystem.Admin_Auth.Username;
import static com.example.billingsystem.Edit_table.table_no;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link } factory method to
 * create an instance of this fragment.
 */
public class Manage_Admin extends Fragment {
    EditText new_username,new_password,new_table;
    Button update;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_manage__admin, container, false);
        new_password=v.findViewById(R.id.new_password);
        new_username=v.findViewById(R.id.new_username);
        new_table=v.findViewById(R.id.new_table);
        update=v.findViewById(R.id.update_admin);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(new_password.getText().toString().length()==0)
                    new_password.setError("Enter New Password");

               else if(new_username.getText().toString().length()==0)
                    new_username.setError("Enter New Username");

               else{
                   if(new_table.getText().toString().length()>0)
                       table_no= Integer.parseInt(new_table.getText().toString());
                   Password=new_password.getText().toString();
                   Username=new_username.getText().toString();
                    Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(getActivity(),Admin_Auth.class));
                }
            }

        });

        return  v;
    }
}