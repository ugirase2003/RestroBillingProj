package com.example.billingsystem;

import static com.example.billingsystem.MainActivity.Added_items;
import static com.example.billingsystem.MainActivity.Total_Global;
import static com.example.billingsystem.OfferFragment.offers;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import adapter.CartAdapter;

/**
 * A simple {@link Fragment} subclass
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

static  int Total_Amount=0,Price_With_Offer=0;
CartAdapter cpAdapter;
RecyclerView recyclerView;
Button Pay;
TextView emptyCartText;
boolean offer_applied=false;
ProgressBar progressBar;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        progressBar=view.findViewById(R.id.cart_load);
        emptyCartText=view.findViewById(R.id.empty_text);
        if(Added_items.size()==0){
            emptyCartText.setVisibility(View.VISIBLE);

        }






        TextView Grand_Total=view.findViewById(R.id.total_amt);
        Pay=view.findViewById(R.id.pay);
       /* TextView price,count,name;
        ImageView img;
        String ds=null;
        Map<String,String>selected_i_item=new HashMap<>();//Map type object
        selected_i_item=Added_items.get(1);
        ds=selected_i_item.get("item_count_key");*/
       recyclerView=view.findViewById(R.id.cart_items_recycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cpAdapter=new CartAdapter(getActivity());
        recyclerView.setAdapter(cpAdapter);
        calTotal();
        cal_offer_total();


        if(offer_applied) {
            TextView original_total= view.findViewById(R.id.amt_text1);
            View dash_line= view.findViewById(R.id.dash_line);
            dash_line.setVisibility(View.VISIBLE);
            original_total.setText("Rs "+Total_Amount);
            Grand_Total.setText("Rs " + Price_With_Offer);
        }
        else{

            Grand_Total.setText("Rs "+Total_Amount);

        }

      Pay.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if (Total_Amount==0){
                  Toast.makeText(getActivity(), "Your Cart is Empty", Toast.LENGTH_SHORT).show();
              }
              else if(offer_applied)
              { progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(getActivity(),Billing_Activity.class).putExtra("total_payable_amt",Price_With_Offer));}
              else
              {
                  progressBar.setVisibility(View.VISIBLE);
                  startActivity(new Intent(getActivity(),Billing_Activity.class).putExtra("total_payable_amt",Total_Amount));}
          }
      });







        return  view;
    }

    private void cal_offer_total() {
        try {


            ArrayList<Integer> order_above_price = new ArrayList<>();


            int applied_above_price;
            for (int i = 0; i < offers.size(); i++) {

                Offer_Model model = offers.get(i);
                order_above_price.add(Integer.valueOf(model.getOffer_above_price()));


            }
            Collections.sort(order_above_price, Collections.reverseOrder());





            for (int i = 0; i < order_above_price.size(); i++) {
                if (Total_Amount >= order_above_price.get(i)) {

                    applied_above_price = order_above_price.get(i);

                    for (int j = 0; j < offers.size(); j++) {
                        Offer_Model model = offers.get(j);
                        if (Integer.parseInt(model.getOffer_above_price()) == applied_above_price) {
                            Price_With_Offer = Total_Amount - Integer.parseInt(model.getOffer_disc_price());
                            offer_applied = true;
                        }

                    }

                    break;

                }

            }
        }catch (Exception e)
        {Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();}
    }

    //it is for calculating total of selected items
    private void calTotal() {
        Total_Amount=0;
        for(int i = 0;i<Added_items.size();i++){
            int Total=Integer.parseInt( (String) Added_items.get(i).get("item_price_key"))*Integer.parseInt((String)   Added_items.get(i).get("item_count_key"));
            Total_Amount=Total_Amount+Total;

        }




    }
}