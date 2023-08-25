package com.example.billingsystem;

import static com.example.billingsystem.MainActivity.Added_items;




import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Menu_Ad extends RecyclerView.Adapter<Menu_Ad.Holder> {
    Context context;
    ArrayList<Menu_Model>list=new ArrayList<>();

    public Menu_Ad(Context context, ArrayList<Menu_Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.dummy_menu_card,parent,false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        //set counter for item in menu if that item is prsent in cart






        Menu_Model model=list.get(position);
        holder.itemName.setText(model.getItem_name());
        holder.price.setText(model.getItem_price());
        holder.status.setText(model.getItem_status());
        holder.edit_btn.setVisibility(View.INVISIBLE);
       // holder.item_counter_show.setText((CharSequence) Added_items.get(position).get("item_count_key"));
        //set url as img tag of image
        holder.img.setTag(model.getImg_url());
      // Toast.makeText(context, holder.img.getTag().toString(), Toast.LENGTH_SHORT).show();

        Picasso.get().load(model.getImg_url()).into(holder.img, new Callback() {
            @Override
            public void onSuccess() {
                //invisble loading bar after loading img
                holder.loading_img.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends  RecyclerView.ViewHolder{
        TextView itemName,price,status,add_btn,edit_btn;
        ImageView img;
        ProgressBar loading_img;
        int  item_count=0;

        public Holder(@NonNull View itemView) {
            super(itemView);
            loading_img=itemView.findViewById(R.id.loading_img);
            itemName=itemView.findViewById(R.id.item_title);
            price=itemView.findViewById(R.id.price);
            status=itemView.findViewById(R.id.status);
            img=itemView.findViewById(R.id.icon_menu);
            add_btn=itemView.findViewById(R.id.add_text_btn);
            edit_btn=itemView.findViewById(R.id.edit_text_btn);


            //here we are setting onclick on add btn so when cust.. selects any item it will added in total in our cart
            add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {






                    //so when add any item the data will gonna store in Map object and after that it will store in Arraylist
                    //of selcted items
                    String item_price,item_name,img_url;

                    int img_id;
                    item_count=1;
                    boolean is_item_to_be_add_in_cart=true;
                    item_price=price.getText().toString();//for getting price of selected item

                    //now get url in the from of img tag and pas it to map object
                    img_url=img.getTag().toString();



                    item_name=itemName.getText().toString();//for getting name of selected item
                    img_id=img.getId();
                    Map<String,String>selected_item=new HashMap<>();//Map type object
                    selected_item.put("item_price_key",item_price);
                    selected_item.put("item_name_key",item_name);
                    selected_item.put("item_img_key",Integer.toString(img_id));
                    selected_item.put("img_url",img_url);
                    selected_item.put("item_count_key",Integer.toString(item_count));//for storing item of count if same item will select


               //     Map<String,String>selected_i_item=new HashMap<>();//Map type object
                 //   selected_i_item=Added_items.get(0);
                //    demos=selected_i_item.get("item_name_key");


                    //here we are checking that is selected item already exist in arraylist if yes then increment of counter
                    for(int i=0;i<Added_items.size();i++){

                        Map<String,String>current_item=new HashMap<>();
                        current_item=Added_items.get(i);
                        String current_item_name=(String)current_item.get("item_name_key");
                        if(current_item_name==item_name){
                            //increment  count of item
                            item_count=Integer.parseInt(current_item.get("item_count_key"))+1;
                            Added_items.get(i).put("item_count_key",Integer.toString(item_count));
                         //   list.get(getAdapterPosition()).setQuantity_counter(item_count);
                            is_item_to_be_add_in_cart=false;
                            break;
                        }


                    }
                    if(is_item_to_be_add_in_cart){
                        //add selcted item in arraylist if it is not existing in list
                        Added_items.add(selected_item);

                    }

                    Toast.makeText(context, "Added in Cart", Toast.LENGTH_SHORT).show();


                }
            });








        }
    }
}
