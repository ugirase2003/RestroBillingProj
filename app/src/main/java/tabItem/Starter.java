package tabItem;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.billingsystem.Menu_Ad;
import com.example.billingsystem.Menu_Model;
import com.example.billingsystem.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class Starter extends Fragment {

    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    ArrayList<Menu_Model> Starter_Item_List=new ArrayList<>();
    Menu_Ad ad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_starter, container, false);

        recyclerView=v.findViewById(R.id.starterRecycler);

        getStarterItems();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ad=new Menu_Ad(getActivity(),Starter_Item_List);
        recyclerView.setAdapter(ad);




        return  v;
    }

    private void getStarterItems() {

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseFirestore.collection("admin").document("menu").collection("menu_items").orderBy("img_url", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                            Menu_Model data=dc.getDocument().toObject(Menu_Model.class);


                            //check if item categroy matches with veg then add it to veg item list
                            if(data.getItem_category().equals("Starter")){

                                Starter_Item_List.add(data);
                            }


                        }
                        catch (Exception e){
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }




                    }
                    ad.notifyDataSetChanged();//it will update adpater instantly after adding new data
                }

            }
        });
    }
}