package com.example.billingsystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class add_offer extends Fragment {
FirebaseFirestore firebaseFirestore;
EditText offer_above_price,offer_disc_price,offer_desc;
Button add_offer;
ProgressBar loading;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v= inflater.inflate(R.layout.fragment_add_offer, container, false);
      offer_above_price=v.findViewById(R.id.on_order_above);
      offer_disc_price=v.findViewById(R.id.offer_price);
      offer_desc=v.findViewById(R.id.offer_disc);
      loading=v.findViewById(R.id.add_offer_load);

      add_offer=v.findViewById(R.id.add_offer);
      add_offer.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              firebaseFirestore=FirebaseFirestore.getInstance();

              if(offer_above_price.getText().toString().length()==0){
                  offer_above_price.setError("Enter Price");
              }

             else if(offer_disc_price.getText().toString().length()==0){
                  offer_above_price.setError("Enter Offer Price");
              }


             else{

                 loading.setVisibility(View.VISIBLE);


                  Map<String ,String>offer=new HashMap<>();
                  offer.put("offer_above_price",offer_above_price.getText().toString());
                  offer.put("offer_disc_price",offer_disc_price.getText().toString());
                  offer.put("offer_desc",offer_desc.getText().toString());

                  firebaseFirestore.collection("offers").add(offer).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                      @Override
                      public void onSuccess(DocumentReference documentReference) {
                          loading.setVisibility(View.INVISIBLE);
                          Toast.makeText(getActivity(), "Offer Added Successfully", Toast.LENGTH_SHORT).show();
                      }
                  }).addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {
                          loading.setVisibility(View.INVISIBLE);
                          Toast.makeText(getActivity(), "Failed to Add Offer", Toast.LENGTH_SHORT).show();
                      }
                  });


              }

          }
      });



      return  v;
    }
}