package adapter;


import  com.example.billingsystem.Edit_Menu;




import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.billingsystem.Edit_Offer;
import com.example.billingsystem.Menu_Ad;
import com.example.billingsystem.Menu_Model;
import com.example.billingsystem.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;


public class Menu_admin_ad extends RecyclerView.Adapter<Menu_admin_ad.Holder> {
    Context context;
    ArrayList<Menu_Model>list=new ArrayList<>();

    public Menu_admin_ad(Context context, ArrayList<Menu_Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.dummy_menu_card,parent,false);

        return new Menu_admin_ad.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Menu_admin_ad.Holder holder, int position) {
        holder.edit_btn.setVisibility(View.VISIBLE);
        Menu_Model model=list.get(position);
        holder.itemName.setText(model.getItem_name());
        holder.price.setText(model.getItem_price());
        holder.status.setText(model.getItem_status());
        holder.add_btn.setVisibility(View.INVISIBLE);
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
        public Holder(@NonNull View itemView) {
            super(itemView);
            loading_img=itemView.findViewById(R.id.loading_img);
            itemName=itemView.findViewById(R.id.item_title);
            price=itemView.findViewById(R.id.price);
            status=itemView.findViewById(R.id.status);
            img=itemView.findViewById(R.id.icon_menu);
            add_btn=itemView.findViewById(R.id.add_text_btn);
            edit_btn=itemView.findViewById(R.id.edit_text_btn);




            edit_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Menu_Model model =list.get(getAdapterPosition());
                    context.startActivity(new Intent(context, Edit_Menu.class).putExtra("menu_data",model));

                    }

            });




        }
    }
}
