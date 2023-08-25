package com.example.billingsystem;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Add_Items extends Fragment {

FirebaseFirestore firebaseFirestore;
Uri image_uri;
Button add_btn;
ImageView selected_img;//choose and insert img in  this view
String img_name;//the name by which img will store in data storage
StorageReference storageReference; // for referring to our fire_storage cloud on which we are gonna store images
Boolean isImageUploaded=false,isGetUrl=false,isImageSelected=false;//is it is true after then we are able to add menu item
String img_url;
ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //getting firestore object(stream which connects to firestore service)
        firebaseFirestore=FirebaseFirestore.getInstance();


        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_add_items,container,false);

        progressBar=view.findViewById(R.id.menu_load);

        Button select_img=view.findViewById(R.id.img_select_btn);//this button  will use for choosing img from device
        Button upload_img=view.findViewById(R.id.img_upload_btn);//this button will use for uploading selected img to on firebase storage

        selected_img=view.findViewById(R.id.item_img);//it is imageview container in which we can see our selected img

        add_btn=view.findViewById(R.id.add_btn);//this button will use for adding data to firestore

        //edittext in which admin will enter all the info related to menu item
        EditText item_name,item_price,item_selling_status,item_category;
        //initialization of edittext
        item_name=view.findViewById(R.id.new_name);
        item_price=view.findViewById(R.id.new_price);
        item_category=view.findViewById(R.id.new_category);
        item_selling_status=view.findViewById(R.id.new_selling_status);



       select_img.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (item_name.getText().toString().length()==0){
                   item_name.setError("Enter Name");
               }
               else{
               img_name=item_name.getText().toString();
               selectImage();}
           }
       });




       upload_img.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               uplaodImg();
               isImageUploaded=true;
           }
       });


       //default state of add button sets as false
      add_btn.setEnabled(false);




        add_btn.setOnClickListener(new View.OnClickListener() {


            //creating document reference and store reference of our document in which will store menu items
            DocumentReference documentReference=firebaseFirestore.collection("admin").document("menu");


            @Override
            public void onClick(View view) {


                //check if any edittext is empty

                if(item_name.getText().toString().trim().length()==0){
                    item_name.setError("Enter Name");


                }
                else if(item_price.getText().toString().trim().length()==0){
                    item_price.setError("Enter Price");

                }
                else if(item_category.getText().toString().trim().length()==0){
                    item_category.setError("Enter Category");
                }
                else if(item_selling_status.getText().toString().trim().length()==0){
                    item_selling_status.setError("Enter Selling Status");
                }

                //if all edit texts are fulfill with info then execute else part


                else{
                 //ImageView img1=view.findViewById(R.id.loadimg);

                    //we aslo need to store uri of uploaded image so here we will fetch that uri






                    progressBar.setVisibility(View.VISIBLE);
                  //get all the info and store it in String format then store this data in firestore
                    String name,price,status,category;
                    name=item_name.getText().toString();
                    price=item_price.getText().toString();
                    status=item_selling_status.getText().toString();
                    category=item_category.getText().toString();


                 //   Toast.makeText(getActivity(), img_url, Toast.LENGTH_SHORT).show();

                    //create map object and store all this values with key pair
                    Map<String,String> New_Item=new HashMap<>();
                    New_Item.put("img_url",img_url);
                    New_Item.put("item_name",name);
                    New_Item.put("item_price",price);
                    New_Item.put("item_category",category);
                    New_Item.put("item_status",status);



                    documentReference.collection("menu_items").add(New_Item).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(), "Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(), "Failed to Add", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });

        return view;
    }



    private void uplaodImg() {
progressBar.setVisibility(View.VISIBLE);
storageReference= FirebaseStorage.getInstance().getReference("item_images/"+img_name);
storageReference.putFile(image_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
    @Override
    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        StorageReference storageReference2=FirebaseStorage.getInstance().getReference("item_images/"+img_name.trim());

        storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                setUrl(uri.toString());
               progressBar.setVisibility(View.INVISIBLE);

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Toast.makeText(getActivity(), "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
        add_btn.setEnabled(true);

    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(getActivity(), "Failed to Upload", Toast.LENGTH_SHORT).show();
    }
});




    }




    private void setUrl(String img_url) {
        this.img_url=img_url;
    }




    private void selectImage() {

        //we want to select image from mobile storage son we need intent
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && data!=null && data.getData()!=null){

            image_uri=data.getData();
            selected_img.setImageURI(image_uri);



        }
    }
}