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

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import adapter.Offer_admin_ad;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link } factory method to
 * create an instance of this fragment.
 */
public class Offer_Admin extends Fragment {
RecyclerView recyclerView;
Offer_admin_ad offer_admin_ad;
ArrayList<Offer_Model>fetch_data=new ArrayList<>();
FirebaseFirestore firebaseFirestore;
TextView default_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View v =inflater.inflate(R.layout.fragment_offer__admin, container, false);
    recyclerView=v.findViewById(R.id.offer_rec_admin);
    default_text=v.findViewById(R.id.offer_admin_text);

    getData();

        return  v;
    }


    private void getData() {
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("offers").orderBy("offer_above_price").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null)
                    Toast.makeText(getActivity(), "Failed To Fetch Data", Toast.LENGTH_SHORT).show();
                else {
                      fetch_data.clear();
                    for(DocumentChange dc:value.getDocumentChanges()){

                        Offer_Model model=dc.getDocument().toObject(Offer_Model.class);
                        model.setDoc_id(dc.getDocument().getId());
                        fetch_data.add(model);

                    }
                 //   offer_admin_ad.notifyDataSetChanged();
                    offer_admin_ad=new Offer_admin_ad(fetch_data,getActivity());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(offer_admin_ad);
                }
            }
        });
    }
}