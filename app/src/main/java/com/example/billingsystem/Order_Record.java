package com.example.billingsystem;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import adapter.Record_Customer;


/**
 * A simple {@link Fragment} subclass.
 * Use the {} factory method to
 * create an instance of this fragment.
 */
public class Order_Record extends Fragment {
RecyclerView recyclerView;
Record_Customer record_customer_adapter;
FirebaseFirestore firebaseFirestore;
ArrayList<Record_Model>data=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_order__record, container, false);
        getData();

        record_customer_adapter=new Record_Customer(data,getActivity());
        recyclerView=v.findViewById(R.id.rec_order_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(record_customer_adapter);


        return  v;
    }

    private void getData() {
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("customer_record").orderBy("c_name").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null)//here we are checking is there any error while fetching data
                {
                    Toast.makeText(getActivity(),"Failed to Fetch Data",Toast.LENGTH_SHORT).show();
                    return;
                }

                for(DocumentChange dc:value.getDocumentChanges()){
                    if(dc.getType()==DocumentChange.Type.ADDED){

                        Record_Model model=dc.getDocument().toObject(Record_Model.class);
                        data.add(model);
                    }

                    record_customer_adapter.notifyDataSetChanged();

                }
            }
        });
    }
}