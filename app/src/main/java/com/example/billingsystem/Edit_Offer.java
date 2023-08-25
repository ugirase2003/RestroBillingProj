package com.example.billingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Edit_Offer extends AppCompatActivity {
    EditText update_order_above,update_offer_price,update_desc;
    Button update,delete;
    FirebaseFirestore firebaseFirestore;
    String price,above_price,desc;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_offer);
        getSupportActionBar().hide();
       Offer_Model model=(Offer_Model) getIntent().getSerializableExtra("data");
        progressBar=findViewById(R.id.menu_load_edit);
        update_order_above=findViewById(R.id.on_order_above_update);
        update_offer_price=findViewById(R.id.offer_price_update);
        update_desc=findViewById(R.id.offer_desc_update);
        update=findViewById(R.id.update_offer);
        delete=findViewById(R.id.del_offer);

        firebaseFirestore=FirebaseFirestore.getInstance();

        //set our clicked item data in edit text and son it will get easy to admin for editing
        update_offer_price.setText(model.offer_disc_price);
        update_order_above.setText(model.offer_above_price);
        update_desc.setText(model.offer_desc);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update_offer_price.getText().toString().trim().length()==0)
                    update_offer_price.setError("Enter Offer price");
                else if(update_order_above.getText().toString().trim().length()==0)
                    update_order_above.setError("Enter Order Above Price");
                else if(update_desc.getText().toString().trim().length()==0 && update_desc.getText().toString().trim().length()<=60)
                    update_desc.setError("Enter Max 60 words");
                else{

                    progressBar.setVisibility(View.VISIBLE);
                    price=update_offer_price.getText().toString().trim();
                    above_price=update_order_above.getText().toString().trim();
                    desc=update_desc.getText().toString().trim();

                    updateData(price,above_price,desc,model.getDoc_id());
                    startActivity(new Intent(Edit_Offer.this,Admin_Activity.class));

                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseFirestore.collection("offers").document(model.getDoc_id()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Edit_Offer.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Edit_Offer.this,Admin_Activity.class));
                    }
                });
            }
        });



    }

    private void updateData(String price, String above_price, String desc, String doc_id) {
     Offer_Model model=new Offer_Model(price,above_price,desc);
     model.setDoc_id(doc_id);
     firebaseFirestore.collection("offers").document(doc_id).set(model).addOnSuccessListener(new OnSuccessListener<Void>() {
         @Override
         public void onSuccess(Void unused) {
             Toast.makeText(Edit_Offer.this, "Updated Successfully", Toast.LENGTH_SHORT).show();

         }
     }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             Toast.makeText(Edit_Offer.this, "Failed to Update", Toast.LENGTH_SHORT).show();
         }
     });

    }
}