package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.billingsystem.R;
import com.example.billingsystem.Record_Model;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

public class Record_Customer extends RecyclerView.Adapter<Record_Customer.Holder> {

    ArrayList<Record_Model>record_model=new ArrayList<>();
    Context context;



    public Record_Customer(ArrayList<Record_Model> record_order, Context context) {
        this.record_model = record_order;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.order_record_recycler,parent,false);
        return new Record_Customer.Holder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Record_Model model=record_model.get(position);
        holder.c_name.setText(model.getC_name());
        holder.c_number.setText(model.getC_number());
        holder.total.setText(model.getGenerated_amt());
    }

    @Override
    public int getItemCount() {
        return record_model.size();
    }

    public class Holder  extends RecyclerView.ViewHolder{
        TextView c_name,c_number,total;
        public Holder(@NonNull View itemView) {
            super(itemView);
            c_name=itemView.findViewById(R.id.rec_cname);
            c_number=itemView.findViewById(R.id.rec_mo);
            total=itemView.findViewById(R.id.rec_total);



        }
    }
}
