package adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.billingsystem.R;
import com.example.billingsystem.current_order_model;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Current_Order_Adapter extends RecyclerView.Adapter<Current_Order_Adapter.holder> {
    ArrayList<current_order_model>total_order;
    Context context;


    public Current_Order_Adapter(ArrayList<current_order_model> total_order, Context context) {
        this.total_order = total_order;
        this.context = context;
    }





    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.current_order_recycler_layout,parent,false);
        return new Current_Order_Adapter.holder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        current_order_model model=total_order.get(position);
        holder.table_no.setText(model.getTable_no());
        holder.total.setText(model.getTotal());
        holder.customer_name.setText(model.getName());

        String total_food_item_list="";
        for(int i=0;i<model.item_list.size();i++){
            total_food_item_list=total_food_item_list+model.item_list.get(i)+"\n";

        }
        holder.item_list.setText(total_food_item_list);


       holder.payment_success.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {





              // holder.del_loading.setVisibility(View.VISIBLE);
               FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
               //delete entry of successful payment order
               //Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
               String docid=model.getDocid();
               firebaseFirestore.collection("current_orders").document(docid).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {
                    Toast.makeText(context,"Order's Payment is Completed",Toast.LENGTH_SHORT).show();
                   }
               });
               //update data source for updating recyclerview
               total_order.remove(holder.getAdapterPosition());
               notifyDataSetChanged();


           }
       });
    }

    @Override
    public int getItemCount() {

        return total_order.size();
    }

    public class holder extends  RecyclerView.ViewHolder{
        TextView table_no,total,customer_name,item_list;
        ProgressBar del_loading;
        Button payment_success;
        public holder(@NonNull View itemView) {
            super(itemView);
            del_loading=itemView.findViewById(R.id.order_delete_loading);
            table_no=itemView.findViewById(R.id.table_no);
            total=itemView.findViewById(R.id.amt_to_be_paid);
            customer_name=itemView.findViewById(R.id.customer_name);
             item_list=itemView.findViewById(R.id.item_list_with_total);
            payment_success=itemView.findViewById(R.id.payment_confirmation);
        }
    }
}
