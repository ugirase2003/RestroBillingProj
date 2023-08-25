package com.example.billingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Edit_Menu extends AppCompatActivity {
    EditText name,price,category,status;
    Button update,delete;
  //  ImageView update_img;
    FirebaseFirestore firebaseFirestore;
    String Name,Price,Category,Status;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        progressBar=findViewById(R.id.menu_load_edit);

        Menu_Model model=(Menu_Model) getIntent().getSerializableExtra("menu_data");
        String img_url=model.getImg_url();
        firebaseFirestore=FirebaseFirestore.getInstance();
        name=findViewById(R.id.update_name);
        price=findViewById(R.id.update_price);
        category=findViewById(R.id.update_category);
        status=findViewById(R.id.update_selling_status);
        delete=findViewById(R.id.delete_menu);
     //   select=findViewById(R.id.update_img_select_btn);
      //  upload=findViewById(R.id.update_img_upload_btn);
        update=findViewById(R.id.update_item_btn);
   //     update_img=findViewById(R.id.update_item_img);

        name.setText(model.getItem_name());
        price.setText(model.getItem_price());
        category.setText(model.getItem_category());
        status.setText(model.getItem_status());

       // Picasso.get().load(model.getImg_url()).into(update_img);

        Name=name.getText().toString();
        Price=price.getText().toString();
        Category=category.getText().toString();
        Status=status.getText().toString();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Name=name.getText().toString();
                Price=price.getText().toString();
                Category=category.getText().toString();
                Status=status.getText().toString();


                if(Name==null){
                    name.setError("Enter Name");


                }
                else if(Price==null)
                    price.setError("Enter Price");
                else if(Category==null ){
                    category.setError("Enter Proper category Veg,Nonveg,Dessert,Fastfood,Starter,Beverages");
                }
                else{

                    Map<String,String>Model=new HashMap<>();
                    Model.put("item_category",Category);
                    Model.put("item_name",Name);
                    Model.put("item_price",Price);
                    Model.put("item_status",Status);
                    Model.put("img_url",img_url);
                    firebaseFirestore.collection("admin").document("menu").collection("menu_items").document(model.getDoc_id()).set(Model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(Edit_Menu.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(Edit_Menu.this,"Failed to Update",Toast.LENGTH_SHORT).show();
                        }
                    });






                }

            }


        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseFirestore.collection("admin").document("menu").collection("menu_items").document(model.getDoc_id()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(Edit_Menu.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(Edit_Menu.this,"Failed to Delete",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}