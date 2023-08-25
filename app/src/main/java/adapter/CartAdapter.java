package adapter;

import static com.example.billingsystem.MainActivity.Added_items;
import static com.example.billingsystem.MainActivity.Total_Global;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.billingsystem.CartFragment;
import com.example.billingsystem.MainActivity;
import com.example.billingsystem.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder> {
    Context context;
    FragmentTransaction fragmentTransaction;

    public CartAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cart_litem_layout,parent,false);
        return new Holder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
     Map<String,String>newmap=new HashMap<>();
     newmap=Added_items.get(position);
     String name,price,total_string;

     int img_id,total=0;
     name=newmap.get("item_name_key");
     price=newmap.get("item_price_key");
     img_id= Integer.parseInt(newmap.get("item_img_key"));

     holder.cart_price.setText(price);
     holder.cart_name.setText(name);
     holder.counter_show.setText(newmap.get("item_count_key"));


     total= Integer.parseInt(newmap.get("item_count_key"))*Integer.parseInt(price);
     total_string=price+" X "+newmap.get("item_count_key")+"= Rs "+total;
     holder.cart_total.setText(total_string);
     //load img in cart img
     Picasso.get().load(newmap.get("img_url")).into(holder.cart_img);

    }

    @Override
    public int getItemCount() {
        return Added_items.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView cart_img;
        TextView cart_name,cart_price,cart_total,remove_btn,counter_show;
        int item_count=1;
        public Holder(@NonNull View itemView) {
            super(itemView);
            cart_name=itemView.findViewById(R.id.cart_item_name);
            cart_price=itemView.findViewById(R.id.cart_price);
            cart_total=itemView.findViewById(R.id.cart_per_item_total);
            cart_img=itemView.findViewById(R.id.cart_img);
            remove_btn=itemView.findViewById(R.id.remove_cart_btn);
            counter_show=itemView.findViewById(R.id.quantity_counter);

            remove_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String,String>model=Added_items.get(getAdapterPosition());

                    item_count= Integer.parseInt(model.get("item_count_key"))-1;

                    if(item_count<=0){
                        Added_items.remove(getAdapterPosition());
                        notifyDataSetChanged();
                        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new CartFragment()).commit();
                    }
                    else{

                        int total;
                        String total_string;
                        String price;
                        price=model.get("item_price_key");

                        model.put("item_count_key",Integer.toString(item_count));
                        counter_show .setText(Integer.toString(item_count));

                        total= Integer.parseInt(model.get("item_count_key"))*Integer.parseInt(price);
                        total_string=price+" X "+model.get("item_count_key")+"= Rs "+total;
                        cart_total.setText(total_string);
                        try {

                            ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.home_frame,new CartFragment()).commit();
                            //fragmentTransaction.replace(R.id.home_frame, new CartFragment(fragmentTransaction)).commit();
                        }
                        catch (Exception e){
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                      //  context.startActivity(new Intent(context, MainActivity.class).putExtra("isRefreshFrag",1));

                    }
                }
            });;

        }
    }
}
