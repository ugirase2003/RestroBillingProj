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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import adapter.Menu_admin_ad;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Admin_Menu} factory method to
 * create an instance of this fragment.
 */
public class Admin_Menu extends Fragment {

RecyclerView recyclerView;
FirebaseFirestore firebaseFirestore;
ArrayList<Menu_Model>list=new ArrayList<>();
Menu_admin_ad menu_admin_ad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_admin__menu, container, false);
        recyclerView=v.findViewById(R.id.rec_admin_menu);
        getData();
        menu_admin_ad=new Menu_admin_ad(getActivity(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(menu_admin_ad);



        return v;
    }

    private void getData() {
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("admin").document("menu").collection("menu_items").orderBy("img_url", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Toast.makeText(getActivity(), "Failed to Fetch Data", Toast.LENGTH_SHORT).show();
                }
                for(DocumentChange dc:value.getDocumentChanges()){
                    if(dc.getType()==DocumentChange.Type.ADDED){
                        try {
                            //remeber that whgile  using toObject method u have to make empty constructor in class in which u want convert ur data
                            Menu_Model data=dc.getDocument().toObject(Menu_Model.class);

                            data.setDoc_id(dc.getDocument().getId());
                                list.add(data);


                        }
                        catch (Exception e){
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        menu_admin_ad.notifyDataSetChanged();


                    }

            }
        }
        });
    }
}