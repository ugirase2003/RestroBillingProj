package adapter;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.billingsystem.Offer_Model;
import com.example.billingsystem.R;

import java.util.ArrayList;

public class Offer_Adapter extends RecyclerView.Adapter<Offer_Adapter.Holder> {
    ArrayList<Offer_Model>offers=new ArrayList<>();
    Context context;

    public Offer_Adapter(ArrayList<Offer_Model> offers, Context context) {
        this.offers = offers;
        this.context = context;
    }



    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.offer_rec,parent,false);
        return new Offer_Adapter.Holder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Offer_Model offer_model=offers.get(position);
        holder.loader.setVisibility(View.INVISIBLE);
        holder.description.setText(offer_model.getOffer_desc());
        holder.disc_off.setText("Get flat Rs "+offer_model.getOffer_disc_price()+" Off");
        holder.order_above.setText("On order above Rs "+offer_model.getOffer_above_price());
        holder.offer_icon.setImageResource(R.drawable.offer);

    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView disc_off,order_above,description;
        ImageView offer_icon;
        ProgressBar loader;
        public Holder(@NonNull View itemView) {
            super(itemView);
            disc_off=itemView.findViewById(R.id.discount_rs);
            order_above=itemView.findViewById(R.id.disc_criteria);
            description=itemView.findViewById(R.id.disc_description);
            offer_icon=itemView.findViewById(R.id.offers_icon);
            loader=itemView.findViewById(R.id.offers_load);
        }
    }
}
