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

import adapter.Offer_Adapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OfferFragment} factory method to
 * create an instance of this fragment.
 */
public class OfferFragment extends Fragment {
RecyclerView offer_rec;
FirebaseFirestore firebaseFirestore;
static  ArrayList<Offer_Model>offers=new ArrayList<>();
Offer_Adapter offer_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_offer, container, false);
        offer_rec=v.findViewById(R.id.offers_recyclerview);

        getOffers();




        return  v;

    }

    private void getOffers() {
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("offers").orderBy("offer_above_price").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Toast.makeText(getActivity(), "Failed To Fetch Data", Toast.LENGTH_SHORT).show();
                }
                offers.clear();
                for(DocumentChange dc:value.getDocumentChanges()){

                    Offer_Model model=dc.getDocument().toObject(Offer_Model.class);
                    model.setDoc_id(dc.getDocument().getId());
                    offers.add(model);
                    offer_adapter.notifyDataSetChanged();
                }


            }
        });
        offer_rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        offer_adapter=new Offer_Adapter(offers,getActivity());
        offer_rec.setAdapter(offer_adapter);

    }
}