package com.example.billingsystem;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
//we have made Adapter for Recyclerview here we are making for SliderView

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder>


{
    Context context;
    int[] img;

    public SliderAdapter(int[] img,Context context) {
        this.img = img;
        this.context=context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.slider_item,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        viewHolder.imageView.setImageResource(img[position]);

    }

    @Override
    public int getCount() {
        return img.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;
        public Holder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);

        }
    }
}
