package com.example.matirialvolley.Sett;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.matirialvolley.R;
import com.example.matirialvolley.databinding.ItemBinding;
import com.example.matirialvolley.models.homeReq;

import java.util.ArrayList;

public class ReAdapter extends RecyclerView.Adapter<ReAdapter.RecHolder> {
    ArrayList<homeReq> deliveryHomeArrayList;

    public ReAdapter(ArrayList<homeReq> deliveryHomeArrayList) {
        this.deliveryHomeArrayList = deliveryHomeArrayList;
    }


    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false); // اضافة التصميم
        return new RecHolder(view);
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    @Override
    public void onBindViewHolder(@NonNull RecHolder holder, int position) {
        homeReq deliveryHome = deliveryHomeArrayList.get(position);
        //
        holder.bind(deliveryHome);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });

    }

    //حجم التكرا عدد العناصر
    @Override
    public int getItemCount() {
        return deliveryHomeArrayList.size();
    }


    static public class RecHolder extends RecyclerView.ViewHolder {
        ItemBinding binding;
        Context context;

        public RecHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemBinding.bind(itemView);
            // احضار الكونتكس
            context = itemView.getContext();
        }


        //تعبئة بيانات اليوزر بالتصميم
        void bind(homeReq deliveryHome) {
            Glide.with(context).load(deliveryHome.getPhoto()).into(binding.image);
            binding.id.setText(deliveryHome.getId() + "");
            binding.workname.setText("Servese tybe :" + deliveryHome.getWork().getName());
            binding.date.setText(deliveryHome.getCreatedAt());
        }
    }
}
