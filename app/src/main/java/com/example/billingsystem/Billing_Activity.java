package com.example.billingsystem;

import static com.example.billingsystem.MainActivity.Added_items;
import static com.example.billingsystem.Edit_table.table_no;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Billing_Activity extends AppCompatActivity {
    EditText c_name,c_number;
    TextView generated_amt;
    Button payment;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        getSupportActionBar().hide();

        //initialization
        payment = findViewById(R.id.payment_btn);
        c_name = findViewById(R.id.c_name);
        c_number = findViewById(R.id.c_number);
        generated_amt = findViewById(R.id.generated_amt);

        generated_amt.setText(Integer.toString(getIntent().getIntExtra("total_payable_amt",0)));

        //get instance of firebasefirestore
        firebaseFirestore = FirebaseFirestore.getInstance();

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


        //check whether edit texts are empty
        if (c_name.getText().toString().trim().length() == 0) {
            c_name.setError("Enter Customer Name");
        }
        if (c_number.getText().toString().trim().length() != 10) {
            c_number.setError("Enter Valid Number");
        }
        else {

                    int size=Added_items.size();



                    CollectionReference collectionReference = firebaseFirestore.collection("customer_record");
                    //new collection will gonna create automatically on referred path so here ith will create various collection in records with
                    //with unique document name or id
                    String C_name, C_number;
                    String generated_total_amt;
                    C_name = c_name.getText().toString();
                    C_number = c_number.getText().toString();



                    generated_total_amt=generated_amt.getText().toString();



             //we are storing below data for permenant record of  customer
                    Map<String, String> customer_record = new HashMap<>();
                    customer_record.put("c_name", C_name);
                    customer_record.put("c_number", C_number);
                    customer_record.put("generated_amt",generated_total_amt);

                    collectionReference.add(customer_record).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                         //   Toast.makeText(Billing_Activity.this, "Data Written Successfully", Toast.LENGTH_SHORT).show();
                       //     startActivity(new Intent(Billing_Activity.this,Bill_Mode.class));
                            //if details gets submit then redirect user on payment mode
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Billing_Activity.this, "Failed to Submit Details", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //storing data in current order
                    ArrayList<String> item_list=new ArrayList<>();
                   for(int i=0;i<size;i++){
                       Map<String,String>item=new HashMap<>();
                       item=Added_items.get(i);
                       int total=Integer.parseInt(item.get("item_price_key"))*Integer.parseInt(item.get("item_count_key"));
                       String item_info=item.get("item_name_key")+" :- "+item.get("item_price_key")+" X "+item.get("item_count_key")+" = "+total+" Rs";
                       //Toast.makeText(Billing_Activity.this, item_info, Toast.LENGTH_SHORT).show();
                       item_list.add(item_info);
                   }

                   Map<String,Object>current_order_item=new HashMap<>();
                   current_order_item.put("item_list",item_list);
                   current_order_item.put("name",C_name);
                   current_order_item.put("table_no", String.valueOf(table_no));
                   current_order_item.put("total",generated_total_amt);
                   FirebaseFirestore current_order_firestore=FirebaseFirestore.getInstance();


                   current_order_firestore.collection("current_orders").add(current_order_item).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                       @Override
                       public void onSuccess(DocumentReference documentReference) {
                           Toast.makeText(Billing_Activity.this,"Order Successfully Placed",Toast.LENGTH_SHORT).show();
                           Added_items.clear();
                           startActivity(new Intent(Billing_Activity.this,Bill_Mode.class));
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(Billing_Activity.this,"Order Not Placed",Toast.LENGTH_SHORT);
                       }
                   });












                  //  CollectionReference collectionReference_for_current_order=firebaseFirestore.collection("current_orders");




        }
            }
        });
    }}