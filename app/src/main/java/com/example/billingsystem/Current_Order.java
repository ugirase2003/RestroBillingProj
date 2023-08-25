package com.example.billingsystem;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import adapter.Current_Order_Adapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {} factory method to
 * create an instance of this fragment.
 */
public class Current_Order extends Fragment {
FirebaseFirestore firebaseFirestore;
Current_Order_Adapter current_order_adapter;
RecyclerView recyclerView;
ArrayList<current_order_model>order_data=new ArrayList<>();
TextView default_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_current__order, container, false);
        recyclerView=view.findViewById(R.id.current_order_recyclerview);

        


       /*****Dummy Data ****/


      //  ArrayList<current_order_model>cp=new ArrayList<>();
            getOrderData();

                current_order_adapter = new Current_Order_Adapter(order_data, getActivity());
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(current_order_adapter);

                Toast.makeText(getActivity(), Integer.toString(order_data.size()), Toast.LENGTH_SHORT).show();

        return  view;
    }

   private void getOrderData() {

        firebaseFirestore=FirebaseFirestore.getInstance();

        CollectionReference collectionReference=firebaseFirestore.collection("current_orders");
        collectionReference.orderBy("item_list", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {


            @Override

            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null)//here we are checking is there any error while fetching data
                {
                    Toast.makeText(getActivity(),"Failed to Fetch Items",Toast.LENGTH_SHORT).show();
                    return;
                }


                for(DocumentChange dc:value.getDocumentChanges()){
                    if(dc.getType()==DocumentChange.Type.ADDED){
                        try {
                            //remeber that whgile  using toObject method u have to make empty constructor in class in which u want convert ur data
                              current_order_model model=dc.getDocument().toObject(current_order_model.class);
                              model.setDocid(dc.getDocument().getId());
                              order_data.add(model);

                           // Toast.makeText(getActivity(), "Successfully", Toast.LENGTH_SHORT).show();




                        }
                        catch (Exception e){
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }




                    }
                    current_order_adapter.notifyDataSetChanged();//it will update adpater instantly after adding new data
                }















            }
        });
    }
}